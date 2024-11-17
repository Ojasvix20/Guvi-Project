const express = require("express");
const db = require("../db");
const router = express.Router();

// Post a new job
router.post("/", (req, res) => {
    const { employer_id, job_title, job_description, required_skills, location } = req.body;
    const sql = "INSERT INTO Jobs (employer_id, job_title, job_description, required_skills, location) VALUES (?, ?, ?, ?, ?)";
    db.query(sql, [employer_id, job_title, job_description, JSON.stringify(required_skills), location], (err, result) => {
        if (err) return res.status(500).send(err);
        res.status(201).send({ message: "Job posted", jobId: result.insertId });
    });
});

// Get jobs by skills
router.get("/", (req, res) => {
    const { skills } = req.query; // Expects a comma-separated list of skills
    const skillArray = skills.split(",");
    const sql = "SELECT * FROM Jobs WHERE JSON_CONTAINS(required_skills, JSON_ARRAY(?))";
    db.query(sql, [skillArray.join('","')], (err, results) => {
        if (err) return res.status(500).send(err);
        res.send(results);
    });
});

module.exports = router;
