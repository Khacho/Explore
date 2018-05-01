var express = require('express');
var router = express.Router();

module.exports = router;

module.exports = function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "*");
    res.header("Access-Control-Allow-Methods", "GET, POST", "PUT", "DELETE");

    next();
};

module.exports = function(app) {
    console.log("Route called");
    var controller = require('../controllers/Controller');
    app.get('/articles/:city', controller.getDataByCity);
    app.get('/article/:id', controller.getDataById);
    app.get('/articleByName/:name', controller.getDataByName);
    app.get('/favorites', controller.getFavorites);
    app.get('/image', controller.getImage);
    app.get('/images', controller.getImagesList);
    app.get('/wallpapers', controller.getWallpaperImagesList);
    app.put('/upload', controller.uploadWallpaperImage);
    app.post('/addArticle', controller.addArticle);
}
