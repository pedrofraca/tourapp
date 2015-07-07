import webapp2
from bs4 import BeautifulSoup
import urllib2, re
import json
from google.appengine.api import urlfetch
from stage import Stage,StageDetails,StageClasification

def generate_tour_data():
    urlfetch.set_default_fetch_deadline(45)
    data_order=["\"date\"","\"stage-icons\"","\"stage-link\"","\"name\"","\"stage-winner\"","\"stage-leader\"","\"km\""]
    page = urllib2.urlopen("http://www.procyclingstats.com/race/Tour_de_France_2015-stages")
    soup = BeautifulSoup(page, "html.parser")
    tabulka = soup.find("table", {"id" : "list5"})
    data={}
    stage_count=0
    for row in tabulka.findAll('tr'):
        col = row.findAll('td')
        data={}
        images=[]
        count=0
        for td in col:
            #Looking for the date
            if(td.string):
                data[data_order[count]]=td.string
            #Do we have icons?
            imgs=td.findAll('img')
            if imgs:
                for img in imgs:
                    images.append(img["\"src\""])
                data[data_order[count]]=images
            #Names for the winner and the leader
            links = td.findAll('a')
            for link in links:
                if(link.string):
                    if "race/" in link['href']:
                        data["\"stage-link\""]="http://www.procyclingstats.com/"+link['href']
                        count+=1
                    data[data_order[count]]=link.string
                count+=1
        if data:
            stage_count+=1
            stage = Stage(id=stage_count,data=str(data))
            stage.put()

def stage_detail_parse(url,stage_number):
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
        if "site-icons" in image["src"]:
            data['stage-icon']=image["src"]
        cont=0

        data['stage-images']=images_json
        
        for element in tabulka.parent:
            #The interesting information doesn't have a tag
            if element.name is None and "\n" not in element.string and element.string !=" " and  "Tag for network 919" not in element.string:
                data[data_order[cont]]=element.string
                cont+=1
        stage_details = StageDetails(id=stage_number,data=str(data))
        stage_details.put()
                


class TourHandler(webapp2.RequestHandler):
    def get(self):
        generate_tour_data()
        q = Stage.query()
        for stage in q:
            d = json.loads(stage.data)
            print d["stage-link"]
        
        
class StageClasificationHandler(webapp2.RequestHandler):
    def get(self):
        data={};
        stage_number = self.request.get("stage")
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
        print len(tables)
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
            print table_count
        self.response.write(json.dumps(data)+"</br>")


class StageHandler(webapp2.RequestHandler):
    def get(self):
        stage_detail_parse("http://www.procyclingstats.com/race/Tour_de_France_2015_Stage_1_Utrecht",1)
            
app = webapp2.WSGIApplication([
    ('/', TourHandler),
    ('/stage',StageHandler),
    ('/clasification',StageClasificationHandler),
], debug=True)
