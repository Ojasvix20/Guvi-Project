const mysql = require("mysql2");

const db = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "password", // Replace with your MySQL password
    database: "skillmatcher",
});

db.connect((err) => {
    if (err) throw err;
    console.log("Connected to MySQL Database");
});

module.exports = db;