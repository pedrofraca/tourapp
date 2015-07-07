import webapp2
from bs4 import BeautifulSoup
import urllib2, re
import json
from google.appengine.api import urlfetch
from stage import Stage,StageDetails,StageClasification
from parser import generate_tour_data,stage_detail_parse,stage_clasification_parser

class TourHandler(webapp2.RequestHandler):
    def get(self):
        self.response.out.write("Getting stages info </br>")
        generate_tour_data()
        q = Stage.query()
        for stage in q:
            d = json.loads(stage.data)
            stage_detail_parse(stage.key.id(),d["stage-link"])
            self.response.out.write("Parsed "+d["stage-link"]+" detail info </br>")
            stage_clasification_parser(stage.key.id())
            self.response.out.write("Parsed "+d["stage-link"]+" clasification  </br>")


class StageClasificationHandler(webapp2.RequestHandler):
    def get(self):
        data={};
        stage_number = self.request.get("stage")
        stage_clasification_parser(stage_number)


class StageHandler(webapp2.RequestHandler):
    def get(self):
        stage_detail_parse("http://www.procyclingstats.com/race/Tour_de_France_2015_Stage_1_Utrecht",1)

app = webapp2.WSGIApplication([
    ('/', TourHandler),
    ('/stage',StageHandler),
    ('/clasification',StageClasificationHandler),
], debug=True)
