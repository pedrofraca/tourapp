import webapp2
import json
from stage import Stage,StageImages,StageClasification
import urllib2, re
from google.appengine.api import urlfetch
from bs4 import BeautifulSoup
import codecs
from google.appengine.api import taskqueue
import random

def images_parser():
    urlfetch.set_default_fetch_deadline(45)
    page = urllib2.urlopen("http://www.letour.fr/le-tour/2015/es/galeria.html")
    soup = BeautifulSoup(page, "html.parser")
    divs = soup.findAll("div",{"class":"diapo v2 photo"})
    for div in divs:
        stage=None
        images_json=[]
        titles=div.findAll("h3")
        for title in titles:
            spans=title.findAll("span")
            for span in spans:
                title_search = re.search('Etapa (.*)', span.string, re.IGNORECASE)
                if title_search:
                    stage = title_search.group(1)
        imgs = div.findAll("img")
        for img in imgs:
            images_json.append(img["lazysrc"].replace("s.jpg","b.jpg"))
        if stage:
            stage_images = StageImages(id=int(stage),data=json.dumps(images_json))
            stage_images.put()

            
    
def stage_clasification_parser(stage_number):
    data={};
    data_order=["pos","rider","country","team","time"]
    data_order_team=["pos","team","time"]
    all_class_order=["stage","general","mountain","team","regularity"]
    urlfetch.set_default_fetch_deadline(45)
    images_json=[]
    if(int(stage_number)>9):
        url=("http://www.marca.com/deporte/ciclismo/tour/clasificaciones/etapa%s.html" % stage_number)
    else:
        url=("http://www.marca.com/deporte/ciclismo/tour/clasificaciones/etapa0%s.html" % stage_number)

    page = urllib2.urlopen(url)
    soup = BeautifulSoup(page, "html.parser")
    tables = soup.findAll("table",{"class":"clasificacion_etapa"})
    table_count=0
    for table in tables:
        stage_clas=[]
        for row in table.findAll('tr'):
            single_row={}
            col = row.findAll('td')
            cont=0
            for td in col:
                if table_count==3:
                    single_row[data_order_team[cont]]=td.string
                else:
                    single_row[data_order[cont]]=td.string
                cont+=1
            if single_row:
                stage_clas.append(single_row)

        data[all_class_order[table_count]]=stage_clas
        table_count+=1
    stage_classification = StageClasification(id=stage_number,data=json.dumps(data))
    stage_classification.put()

def stage_detail_parse(stage_number,url):
    data={}
    urlfetch.set_default_fetch_deadline(45)
    images_json=[]
    data_order=["day","month","avg-speed","cat","start-finish"]
    page = urllib2.urlopen(url)
    soup = BeautifulSoup(page, "html.parser")
    tabulka = soup.find("h3", {"class" : "section"})
    div = tabulka.parent
    images = soup.findAll('img')
    for image in images:
        if "Stage" in image["src"]:
            images_json.append(image["src"])
        if "Final_GC" in image["src"]:
            images_json.append(image["src"])
        if "site-icons" in image["src"]:
            data['stage-icon']=image["src"]
        cont=0

        data['stage-images']=images_json

        for element in tabulka.parent:
            if(cont<len(data_order)):
                if element.name is None and "\n" not in element.string and element.string !=" " and  "Tag for network 919" not in element.string:
                    #The interesting information doesn't have a tag
                    data[data_order[cont]]=element.string
                    cont+=1
    print stage_number
    stage=Stage.get_by_id(int(stage_number))
    stage_data=json.loads(stage.data)
    stage_data.update(data)
    stage.data=json.dumps(stage_data)
    stage.put()


def generate_tour_data():
    urlfetch.set_default_fetch_deadline(45)
    data_order=["date","stage-icons","stage-link","name","stage-winner","stage-leader","km"]
    page = urllib2.urlopen("http://www.procyclingstats.com/race/Tour_de_France_2015-stages")
    soup = BeautifulSoup(page, "html.parser")
    tabulka = soup.find("table", {"id" : "list5"})
    data={}
    stage_count=0
    for row in tabulka.findAll('tr'):
        col = row.findAll('td')
        data={}
        images=[]
        count=1
        for td in col:
            #Looking for the date
            if(td.string):
                if(count==1):
                    data["date"]=td.string
                    count+=1
            #Do we have icons?
            imgs=td.findAll('img')
            if imgs:
                for img in imgs:
                    images.append(img["src"])
                data["stage_icons"]=images
            #Names for the winner and the leader
            links = td.findAll('a')

            for link in links:
                if count==2:
                    if(link.string):
                        data["name"]=link.string
                        data["stage-link"]="http://www.procyclingstats.com/"+link['href']
                    else:
                        data["stage-link"]=""
                if count==3:
                    if(link.string):
                        data["stage-winner"]=link.string
                    else:
                        data["stage-winner"]=""
                if count==4:
                    if(link.string):
                        try:
                            km = float(link.string)
                            data["km"]=link.string
                            data["stage-leader"]=""
                        except ValueError:
                            data["stage-leader"]=link.string
                    else:
                        data["stage-leader"]=""
                if count==5:
                    if(link.string):
                        data["km"]=link.string
                    else:
                        data["km"]=""

                count+=1

        if data:
            stage_count+=1
            if(stage_count<22):
                stage = Stage(id=stage_count,data=json.dumps(data))
                stage.put()

class TourHandler(webapp2.RequestHandler):
    def get(self):
        q = Stage.query()
        stages=[]
        for stage in q:
            stages.append(json.loads(stage.data))
        self.response.headers['Content-Type'] = 'application/json'
        self.response.out.write(json.dumps(stages))

class TaskHandler(webapp2.RequestHandler):
    def get(self):
        self.response.out.write("Getting stages info </br>")
        generate_tour_data()
        q = Stage.query()
        for stage in q:
            d = json.loads(stage.data)
            # Add the task to the default queue.
            taskqueue.add(url='/details', params={'stage_key': stage.key.id(),'stage_link':d["stage-link"]})
            taskqueue.add(url='/clasification', params={'stage_key': stage.key.id()})
            taskqueue.add(url='/image', params={})
            self.response.out.write("Created Task for stage number: <b>"+str(stage.key.id())+"</b></br>")


class StageDetailsHandler(webapp2.RequestHandler):
    def post(self):
        stage_key = self.request.get('stage_key')
        stage_link = self.request.get('stage_link')
        stage_detail_parse(stage_key,stage_link)
        print "Stage Details success for " + stage_link

    def get(self):
        stage_key = self.request.get('stage')
        stage_details=StageDetails.get_by_id(stage_key)
        self.response.headers['Content-Type'] = 'application/json'
        self.response.out.write(json.dumps(stage_details.data))

class StageClasificationHandler(webapp2.RequestHandler):
    def post(self):
        stage_key = self.request.get('stage_key')
        stage_clasification_parser(stage_key)
        print "Stage Clasification success for " + stage_key

    def get(self):
        stage_key = self.request.get('stage')
        stage_details=StageClasification.get_by_id(stage_key)
        self.response.headers['Content-Type'] = 'application/json'
        self.response.out.write(json.dumps(stage_details.data))

class ImageHandler(webapp2.RequestHandler):
    def get(self):
        stage_key = self.request.get('stage')
        stage=StageImages.get_by_id(int(stage_key))
        if stage:
            dict = json.loads(stage.data)
            self.redirect(str(dict[random.randint(0,len(dict))]))
        else:
            self.redirect("http://c1.staticflickr.com/9/8256/8701161794_02c1243f4f_n.jpg")
    def post(self):
        images_parser()


app = webapp2.WSGIApplication([
    ('/tour', TourHandler),
    ('/details', StageDetailsHandler),
    ('/tasks', TaskHandler),
    ('/image', ImageHandler),
    ('/clasification', StageClasificationHandler)
], debug=True)
