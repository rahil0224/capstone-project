const mongoose = require('mongoose')

const userSchema = new mongoose.Schema({
    name:String,
    email:String,
    password:String
})
// Here we can create a Module for DB connection 
module.exports = mongoose.model("users",userSchema);
