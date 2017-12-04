var express = require('express');
var router = express.Router();

module.exports = router;

module.exports = function(app) {
    console.log("Route called");
    var controller = require('../controllers/Controller');
    app.get('/articles/:city', controller.getDataByCity);
    app.get('/article/:id', controller.getDataById);
    app.get('/image', controller.getImage);
}
