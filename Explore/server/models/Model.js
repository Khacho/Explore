// Database connetcion optioins
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
var formidable = require('formidable');
const fs = require('fs');

/*
 * Gets infornation by city
 */
module.exports.getDataByCity = function(req, res) {
    db.transaction(function*(t) {
        var data = (yield t.query("select d.id, d.title, d.data, d.wallpaper_image, d.images_folder, d.building_date, c1.city, c.country, d.latitude, d.longitude from data d inner join location l on d.location_id=l.id inner join country_list c on l.country_id=c.id inner join city_list c1 on l.city_id=c1.id where c1.city='" + req.params.city.substring(1) + "';")).rows;
        console.log("Called");
        if(data.length) { 
            res.send(data);
        } else {
            res.status('404').json({error: 'Not found'});
        }
    });
};

/*
 * Gets infornation by id
 */
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


/*
 * Gets infornation by title
 */
module.exports.getDataByName = function(req, res) {
    console.log('data by name >>> ', req.params.name.substring(1));
    db.transaction(function*(t) {
        var data = (yield t.query("select d.id, d.title, d.data, d.wallpaper_image, d.images_folder, d.building_date, c1.city, c.country, d.latitude, d.longitude from data d inner join location l on d.location_id=l.id inner join country_list c on l.country_id=c.id inner join city_list c1 on l.city_id=c1.id where d.title like '%" + req.params.name.substring(1) + "%';")).rows;
        console.log("Called");
        
         if(data.length) { 
            res.send(data);
        } else {
            res.status('404').json({error: 'Not found'});
        }
    });
};


/*
 * Gets infornation by id list.
 */
module.exports.getFavorites = function(req, res) {
    console.log(req.query.list);
    resList = [];
   
    if(req.query.length == 0) {
        res.send('Not found');
    }
    req.query.list.forEach(function(id, i, arr) {

        db.transaction(function*(t) {
            var data = (yield t.query("select d.id, d.title, d.data, d.wallpaper_image, d.images_folder, d.building_date, c1.city, c.country, d.latitude, d.longitude from data d inner join location l on d.location_id=l.id inner join country_list c on l.country_id=c.id inner join city_list c1 on l.city_id=c1.id where d.id='" + id + "';")).rows;
            resList.push(data[0])
            if(i == req.query.list.length - 1) {
                console.log(resList);
                res.send(resList);
            }
        });
    });

};

/*
 * Adde new article
 */
module.exports.addArticle = function(req, res) {
    const form = new formidable.IncomingForm();
    form.multiples = false;
    form.parse(req, function(err, fields, files) {
        addArticleToDatabase(JSON.parse(fields.article));
    });
    form.on('end', function () {
        res.header("Access-Control-Allow-Origin", "*");
        res.header("Access-Control-Allow-Headers", "*");
        res.header("Access-Control-Allow-Methods", "GET, POST", "PUT", "DELETE");
        res.status(200).json({ status: 'success' });
    });
}

/*
 * Gets images list
 */
module.exports.getImagesList = function(req, res) {
    var dirName = path.join(__dirname, '../resources/images', req.query.dirName);
    console.log(dirName);
    if (!fs.existsSync(dirName)) {
        console.log("not exists");
        res.send({});
        return;
    }
    var jsonRes = []
    var key = "url_list"
    // jsonRes[key] = [];
    fs.readdir(dirName, (err, files) => {
        files.forEach(file => {
            console.log(file);
            url = {
                "small": req.query.dirName + "/"  + file,
                "medium": req.query.dirName + "/" + file,
                "large":  req.query.dirName + "/" + file
            }
            obj = {
                "url": url,
                "timestamp": ""
            }
            jsonRes.push(obj);
        });
        res.send(jsonRes);
    })
}

/*
 * Gets wallpaper images list
 */
module.exports.getWallpaperImagesList = function(req, res) {
    var jsonRes = []
    var key = "url_list"
 
    db.transaction(function*(t) {
        var files = (yield t.query("select wallpaper_image from data;")).rows;
        files.forEach(file => {
            if("" != file.wallpaper_image && null != file.wallpaper_image ) {
                console.log(file.wallpaper_image)
                url = {
                    "small": file.wallpaper_image,
                    "medium": file.wallpaper_image,
                    "large":  file.wallpaper_image
                }
                obj = {
                    "url": url,
                    "timestamp": ""
                }
                jsonRes.push(obj);
            }
            
        });

        res.send(jsonRes);
    });

}

/*
 * Gets image from directory
 */
module.exports.getImage = function(req, res) {
    var file =  path.join(__dirname, '../resources/images', req.query.path); 
    console.log(file)
    console.log('get image')
    res.sendFile(file);
}

module.exports.uploadWallpaperImage = function(req, res) { 
    const dirPath = './resources/images/' + req.query.dirPath + '/';
    console.log('upload - ', dirPath);
    let filePath;
    if (!fs.existsSync(dirPath)) {
        console.log('File does not consist.')
        fs.mkdirSync(dirPath);
    }
    const form = new formidable.IncomingForm();
    form.multiples = false;
    form.uploadDir = path.resolve(dirPath);
    form.on('file', function (field, file) {
        var ext = file.name.split('.');
        var id = makeid();
        filePath = req.query.dirPath + '/' + id + '.' + ext[ext.length - 1];
        console.log('ext >>>>>>>>>>>>> ', filePath);
        fs.rename(file.path, path.join(form.uploadDir, id + '.' + ext[ext.length - 1]));
    });
    form.on('error', function (err) {
        res.status(500).json({ status: 'Internal server error' });
    });
    form.on('end', function () {
        res.header("Access-Control-Allow-Origin", "*");
        res.header("Access-Control-Allow-Headers", "*");
        res.header("Access-Control-Allow-Methods", "GET, POST", "PUT", "DELETE");
        res.status(200).json({ status: 'success', path: filePath, dir: req.query.dirPath });
    });
    form.parse(req);
}

function addArticleToDatabase(article) {
     db.transaction(function*(t) {
       (yield t.query("insert into data (title, data, location_id, building_date, wallpaper_image, images_folder, latitude, longitude)values ('" + article.title + "', '" + article.data + "', " + article.location_id + ", '" + article.building_date + "', '" + article.wallpaper_image + "', '" + article.images_folder + "', " + article.latitude + ", " + article.longitude + ");")).rows;
    });
   console.log('article >>> ', "insert into data (title, data, location_id, building_date, wallpaper_image, images_folder, latitude, longitude)values ('" + article.title + "', '" + article.data + "', " + article.location_id + ", '" + article.building_date + "', '" + article.wallpaper_image + "', '" + article.images_folder + "', " + article.latitude + ", " + article.longitude + ");");
}

function makeid() {
  var text = "";
  var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  for (var i = 0; i < 10; i++)
    text += possible.charAt(Math.floor(Math.random() * possible.length));

  return text;
}
