var model = require('../models/Model');


module.exports.getDataByCity = function(req, res) {
    model.getDataByCity(req,res);
}

module.exports.getDataById = function(req, res) {
    model.getDataById(req,res);
}