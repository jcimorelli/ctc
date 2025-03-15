<#include "header.ftl">
<h2>Leaderboard</h2>
<table id="leaderboardTable">
    <thead>
    <tr>
        <th onclick="sortTable(0)">Entrant</th>
        <th onclick="sortTable(1)">Correct Picks</th>
        <th onclick="sortTable(2)">Correct Upsets</th>
        <th onclick="sortTable(3)">Correct Champions</th>
        <th onclick="sortTable(4)">Total Points</th>
        <th onclick="sortTable(5)">Upset Points</th>
        <th onclick="sortTable(6)">Points On Table</th>
    </tr>
    </thead>
    <tbody>
    <#list leaderboard as row>
        <tr>
            <td>${row.nickname}<#if row.playingForFun>*</#if></td>
            <td>${row.totalCorrectPicks}</td>
            <td>${row.totalCorrectUpsets}</td>
            <td>${row.totalCorrectChampions}</td>
            <td>${row.totalPoints}</td>
            <td>${row.totalUpsetPoints}</td>
            <td>${row.pointsOnTable}</td>
        </tr>
    </#list>
    </tbody>
</table>

<script>
    let sortDirections = {}; // Keep track of each column's sort direction

    function sortTable(colIndex) {
        const table = document.getElementById("leaderboardTable");
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

<#include "footer.ftl">
