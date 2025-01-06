const express = require("express");
const db = require("../db");
const router = express.Router();

// Register a new user
router.post("/register", (req, res) => {
    const { name, email, password, role, skills } = req.body;
    const sql = "INSERT INTO Users (name, email, password, role, skills) VALUES (?, ?, ?, ?, ?)";
    db.query(sql, [name, email, password, role, JSON.stringify(skills)], (err, result) => {
        if (err) return res.status(500).send(err);
        res.status(201).send({ message: "User registered", userId: result.insertId });
    });
});

// Login a user
router.post("/login", (req, res) => {
    const { email, password } = req.body;
    const sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
    db.query(sql, [email, password], (err, results) => {
        if (err) return res.status(500).send(err);
        if (results.length === 0) return res.status(401).send({ message: "Invalid credentials" });
        res.send({ message: "Login successful", user: results[0] });
    });
});

module.exports = router;
