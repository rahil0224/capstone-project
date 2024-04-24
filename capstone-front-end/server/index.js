const express = require("express");
require("./db/config");
const app = express();
const User = require('./db/User');
const cors = require("cors");

app.use(express.json());
app.use(cors({
    origin: 'http://localhost:7080',
    credentials: true
}));



app.post("/register", async (req, resp) => {
    let user = new User(req.body)
    let result = await user.save();
    result = result.toObject();
    delete result.password;
    resp.send(result);
})

app.post("/login",async(req,resp)=>{
    if(req.body.password && req.body.email){
    let user = await User.findOne(req.body).select("-password");
    if(user){
        resp.send(user)
    }else{
        resp.send({result:"user not exist"})
    }
}else{
    resp.send({result:"no user found"})
}
})

const port = process.env.PORT || 7081;
app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});
