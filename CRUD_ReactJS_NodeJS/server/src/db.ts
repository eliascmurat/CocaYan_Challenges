const mysql = require("mysql2");
require("dotenv").config();

const con = mysql.createConnection({
  host: process.env.HOST,
  user: process.env.USER,
  password: process.env.PASSWORD,
  database: process.env.DATABASE
});

con.connect(function(err: any) {
  if (err) throw err;
});

module.exports = con;