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
        <th onclick="sortPicksTable(0,'picksViewTable')">Entrant</th>
        <th onclick="sortPicksTable(1,'picksViewTable')">Conference</th>
        <th onclick="sortPicksTable(2,'picksViewTable')">Round</th>
        <th onclick="sortPicksTable(3,'picksViewTable')">Team</th>
        <th onclick="sortPicksTable(4,'picksViewTable')">Round Points</th>
        <th onclick="sortPicksTable(5,'picksViewTable')">Upset Points</th>
        <th onclick="sortPicksTable(6,'picksViewTable')">Total Points</th>
        <th onclick="sortPicksTable(7,'picksViewTable')">Result</th>
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

<#include "footer.ftl">
