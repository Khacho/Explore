var options = {
   user: 'postgres',
   host: '127.0.0.1',
   db: 'explore',
   password: 'root',
   port: 5432,
};

var db = require('postgres-gen')(options);

var dao = require('postgres-gen-dao');
var data_table = dao({ db: db, table: 'data' });
var path = require('path');


module.exports.getDataByCity = function(req, res) {
    db.transaction(function*(t) {
        var data = (yield t.query("select d.title, d.data, d.wallpaper_image, d.images_folder, d.building_date, c1.city, c.country, d.latitude, d.longitude from data d inner join location l on d.location_id=l.id inner join country_list c on l.country_id=c.id inner join city_list c1 on l.city_id=c1.id where c1.city='" + req.params.city.substring(1) + "';")).rows;
        console.log("Called");
        if(data.length) { 
            res.send(data);
        } else {
            res.status('404').json({error: 'Not found'});
        }
    });
};
module.exports.getDataById = function(req, res) {
    db.transaction(function*(t) {
        var data = (yield t.query("select d.id, d.title, d.data, d.wallpaper_image, d.images_folder, d.building_date, c1.city, c.country, d.latitude, d.longitude from data d inner join location l on d.location_id=l.id inner join country_list c on l.country_id=c.id inner join city_list c1 on l.city_id=c1.id where d.id='" + req.params.id.substring(1) + "';")).rows;
        console.log("Called");
        
         if(data.length) { 
            res.send(data);
        } else {
            res.status('404').json({error: 'Not found'});
        }
    });
};

module.exports.getImage = function(req, res) {
    var file =  path.join(__dirname, '../resources/images', req.query.path); 
    res.sendFile(file);
}
