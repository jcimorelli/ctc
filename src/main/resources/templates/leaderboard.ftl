<#include "header.ftl">
<h2>Leaderboard</h2>
<table>
    <thead>
    <tr>
        <th>Entrant</th>
        <th>Correct Picks</th>
        <th>Correct Upsets</th>
        <th>Correct Champions</th>
        <th>Total Points</th>
        <th>Upset Points</th>
        <th>Points On Table</th>
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
