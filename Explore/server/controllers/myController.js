var model = require('../models/myModel');


module.exports.getDataByCity = function(req, res) {
    model.getDataByCity(req,res);
}

