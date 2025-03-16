<#include "header.ftl">
<h2>CTC Leaderboard</h2>

<div class="filter-section">
    <form action="/leaderboard" method="get">
        <div class="combo-group">
            <label for="conferenceId">Conference:</label>
            <select id="conferenceId" name="conferenceId">
                <option value="0">-- All Conferences --</option>
                <#list conferenceOptions as conference>
                    <option value="${conference.conferenceId}" <#if (conferenceId?? && conferenceId?number == conference.conferenceId)>selected</#if>>
                        ${conference.ctcName}
                    </option>
                </#list>
            </select>
        </div>
        <div class="combo-group">
            <label for="gameDay">Game Day:</label>
            <select id="gameDay" name="gameDay">
                <option value="">-- All Days --</option>
                <#list gameDays as day>
                    <option value="${day}" <#if (gameDay?? && gameDay == day)>selected</#if>>${day}</option>
                </#list>
            </select>
        </div>
        <div class="combo-group">
            <label>&nbsp;</label>
            <button type="submit" class="btn">Filter</button>
        </div>
    </form>
</div>

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
