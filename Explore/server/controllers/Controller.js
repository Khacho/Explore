var model = require('../models/Model');


module.exports.getDataByCity = function(req, res) {
    model.getDataByCity(req,res);
}

module.exports.getDataById = function(req, res) {
    model.getDataById(req,res);
}

module.exports.getDataByName = function(req, res) {
    console.log('Data by name controller');
    model.getDataByName(req,res);
}

module.exports.getFavorites = function(req, res) {
    model.getFavorites(req,res);
}

module.exports.getImage = function(req, res) {
    model.getImage(req,res);
}

module.exports.getImagesList = function(req, res) {
    model.getImagesList(req,res);
}

module.exports.getWallpaperImagesList = function(req, res) {
    model.getWallpaperImagesList(req,res);
}

module.exports.uploadWallpaperImage = function(req, res) {
    model.uploadWallpaperImage(req,res);
}

module.exports.addArticle = function(req, res) {
    model.addArticle(req,res);
}

