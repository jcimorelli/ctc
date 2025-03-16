<#include "header.ftl">
<h2>Game Results</h2>

<table id="gameResultsTable">
    <thead>
    <tr>
        <th onclick="sortTable(0,'gameResultsTable')">Game Date</th>
        <th onclick="sortTable(1,'gameResultsTable')">Conference</th>
        <th onclick="sortTable(2,'gameResultsTable')">Round</th>
        <th onclick="sortTable(3,'gameResultsTable')">Winning Team</th>
        <th onclick="sortTable(4,'gameResultsTable')">Losing Team</th>
        <th onclick="sortTable(5,'gameResultsTable')">Point Value</th>
        <th onclick="sortTable(6,'gameResultsTable')">Who Got It</th>
    </tr>
    </thead>
    <tbody>
    <#list gameResults as result>
        <tr>
            <td>${result.gameDate}</td>
            <td>${result.conference}</td>
            <td>${result.round}</td>
            <td>${result.winningTeam}</td>
            <td>${result.losingTeam}</td>
            <td>${result.pointValue}</td>
            <td>
                <#if result.entrantWinners?size == 1>
                    Just ${result.entrantWinners[0]}!
                <#else>
                    <span class="tooltip">
                      ${result.entrantWinners?size} People
                      <span class="tooltiptext">${result.entrantWinners?join(', ')}</span>
                    </span>
                </#if>
            </td>

        </tr>
    </#list>
    </tbody>
</table>

<#include "footer.ftl">
