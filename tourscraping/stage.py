from google.appengine.ext import ndb

class Stage(ndb.Model):
    data = ndb.StringProperty(indexed=False)

class StageClasification(ndb.Model):
    data = ndb.StringProperty(indexed=False)

class StageDetails(ndb.Model):
    data = ndb.StringProperty(indexed=False)
