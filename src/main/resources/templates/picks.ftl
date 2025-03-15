<#include "header.ftl">
<h2>Picks</h2>

<div class="filter-section">
    <form action="/picks" method="get">
        <div class="form-group">
            <label for="entrantId">Entrant:</label>
            <select id="entrantId" name="entrantId">
                <option value="0">-- All Entrants --</option>
                <#list entrantOptions as entrant>
                    <option value="${entrant.entrantId}" <#if (entrantId?? && entrantId?number == entrant.entrantId)>selected</#if>>
                        ${entrant.nickname}
                    </option>
                </#list>
            </select>

            <label for="conferenceId">Conference:</label>
            <select id="conferenceId" name="conferenceId">
                <option value="0">-- All Conferences --</option>
                <#list conferenceOptions as conference>
                    <option value="${conference.conferenceId}" <#if (conferenceId?? && conferenceId?number == conference.conferenceId)>selected</#if>>
                        ${conference.ctcName}
                    </option>
                </#list>
            </select>

            <label for="teamName">Team:</label>
            <select id="teamName" name="teamName">
                <option value="">-- All Teams --</option>
                <#list teamOptions as option>
                    <option value="${option}" <#if (teamName?? && teamName == option)>selected</#if>>${option}</option>
                </#list>
            </select>
        </div>
        <button type="submit" class="btn">Show</button>
    </form>
</div>

<table id="picksViewTable">
    <thead>
    <tr>
        <th onclick="sortPicksTable(0)">Entrant</th>
        <th onclick="sortPicksTable(1)">Conference</th>
        <th onclick="sortPicksTable(2)">Round</th>
        <th onclick="sortPicksTable(3)">Team</th>
        <th onclick="sortPicksTable(4)">Round Points</th>
        <th onclick="sortPicksTable(5)">Upset Points</th>
        <th onclick="sortPicksTable(6)">Total Points</th>
        <th onclick="sortPicksTable(7)">Result</th>
    </tr>
    </thead>
    <tbody>
    <#list picks as pick>
        <tr class="<#if pick.result?has_content && pick.result == 'CORRECT'>correct<#elseif pick.result?has_content && pick.result == 'INCORRECT'>incorrect</#if>">
            <td>${entrantNames[pick.entrantId?string]}</td>
            <td>${conferenceNames[pick.conferenceId?string]}</td>
            <td>${pick.round}</td>
            <td>${pick.teamName}</td>
            <td>${pick.weightedRoundPoints}</td>
            <td>${pick.weightedUpsetPoints}</td>
            <td>${pick.totalPotentialPoints}</td>
            <td>${pick.result}</td>
        </tr>
    </#list>
    </tbody>
</table>

<script>
    let picksSortDirections = {}; // Store sort direction for each column

    function sortPicksTable(colIndex) {
        const table = document.getElementById("picksViewTable");
        const tbody = table.querySelector("tbody");
        const rows = Array.from(tbody.querySelectorAll("tr"));

        // Determine current sort direction; default to ascending
        let currentDirection = picksSortDirections[colIndex] || "asc";
        let newDirection = (currentDirection === "asc") ? "desc" : "asc";
        picksSortDirections[colIndex] = newDirection;

        // Optionally, update header classes for visual feedback (similar to earlier solution)
        const headerCells = table.querySelectorAll("thead th");
        headerCells.forEach((th, index) => {
            th.classList.remove("sorted-asc", "sorted-desc");
            if (index === colIndex) {
                th.classList.add(newDirection === "asc" ? "sorted-asc" : "sorted-desc");
            }
        });

        // Sort rows based on the specified column.
        rows.sort((rowA, rowB) => {
            let cellA = rowA.querySelectorAll("td")[colIndex].textContent.trim();
            let cellB = rowB.querySelectorAll("td")[colIndex].textContent.trim();

            // Attempt numeric conversion first.
            let valA = parseFloat(cellA.replace(/,/g, ""));
            let valB = parseFloat(cellB.replace(/,/g, ""));
            if (isNaN(valA) || isNaN(valB)) {
                // Fallback to string comparison
                valA = cellA.toLowerCase();
                valB = cellB.toLowerCase();
            }
            if (valA < valB) return (newDirection === "asc") ? -1 : 1;
            if (valA > valB) return (newDirection === "asc") ? 1 : -1;
            return 0;
        });

        // Re-append sorted rows to the tbody.
        rows.forEach(row => tbody.appendChild(row));
    }
</script>

<#include "footer.ftl">
