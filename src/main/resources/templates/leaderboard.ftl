<#include "header.ftl">
<h2>CTC Leaderboard</h2>
<table id="leaderboardTable">
    <thead>
    <tr>
        <th onclick="sortTable(0,'leaderboardTable')">Entrant</th>
        <th onclick="sortTable(1,'leaderboardTable')">Correct Picks</th>
        <th onclick="sortTable(2,'leaderboardTable')">Correct Upsets</th>
        <th onclick="sortTable(3,'leaderboardTable')">Correct Champions</th>
        <th onclick="sortTable(4,'leaderboardTable')">Total Points</th>
        <th onclick="sortTable(5,'leaderboardTable')">Upset Points</th>
        <th onclick="sortTable(6,'leaderboardTable')">Points On Table</th>
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

<#include "footer.ftl">
