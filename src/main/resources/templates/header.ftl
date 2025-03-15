<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle!"CTC"}</title>
    <link rel="icon" href="/favicon.png" type="image/png">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="/leaderboard"><h1>CTC</h1></a></li>
            <!--<li><a href="/conferenceWriteups">Conference Write-ups</a></li>-->
            <li><a href="/leaderboard">Leaderboard</a></li>
            <li><a href="/picks">Picks</a></li>
            <li><a href="/results">Game Results</a></li>
            <li><a href="/pickEntry">Pick Entry</a></li>
            <li><a href="/resultEntry">Game Result Entry</a></li>
        </ul>
    </nav>
</header>

<script>
    let sortDirections = {}; // Keep track of each column's sort direction

    function sortTable(colIndex,tableElementId) {
        const table = document.getElementById(tableElementId);
        const tbody = table.querySelector("tbody");
        const rows = Array.from(tbody.querySelectorAll("tr"));

        // Determine the current sort direction for this column; default to ascending
        let currentDirection = sortDirections[colIndex] || "asc";
        let newDirection = (currentDirection === "asc") ? "desc" : "asc";
        sortDirections[colIndex] = newDirection;

        // Clear sorted classes from all header cells
        const headerCells = table.querySelectorAll("thead th");
        headerCells.forEach((th, index) => {
            th.classList.remove("sorted-asc", "sorted-desc");
            if (index === colIndex) {
                th.classList.add(newDirection === "asc" ? "sorted-asc" : "sorted-desc");
            }
        });

        // Sort the rows based on the column's content
        rows.sort((rowA, rowB) => {
            let cellA = rowA.querySelectorAll("td")[colIndex].textContent.trim();
            let cellB = rowB.querySelectorAll("td")[colIndex].textContent.trim();

            // Try parsing as numbers first; if not, compare as strings
            let valA = parseFloat(cellA.replace(/,/g, ""));
            let valB = parseFloat(cellB.replace(/,/g, ""));
            if (isNaN(valA) || isNaN(valB)) {
                valA = cellA.toLowerCase();
                valB = cellB.toLowerCase();
            }

            if (valA < valB) return newDirection === "asc" ? -1 : 1;
            if (valA > valB) return newDirection === "asc" ? 1 : -1;
            return 0;
        });

        // Re-append the sorted rows to the tbody
        rows.forEach(row => tbody.appendChild(row));
    }
</script>

<div class="container">
<#include "alerts.ftl">