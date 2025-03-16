<#include "header.ftl">
<h2>Picks</h2>

<div class="filter-section">
    <form action="/picks" method="get">
        <div class="combo-group">
            <label for="entrantId">Entrant:</label>
            <select id="entrantId" name="entrantId">
                <option value="0">-- All Entrants --</option>
                <#list entrantOptions as entrant>
                    <option value="${entrant.entrantId}" <#if (entrantId?? && entrantId?number == entrant.entrantId)>selected</#if>>
                        ${entrant.nickname}
                    </option>
                </#list>
            </select>
        </div>
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
            <label for="teamName">Team:</label>
            <select id="teamName" name="teamName">
                <option value="">-- All Teams --</option>
                <#list teamOptions as option>
                    <option value="${option}" <#if (teamName?? && teamName == option)>selected</#if>>${option}</option>
                </#list>
            </select>
        </div>
        <div class="combo-group">
            <label>&nbsp;</label>
            <button type="submit" class="btn">Filter</button>
        </div>
    </form>
</div>

<table id="picksViewTable">
    <thead>
    <tr>
        <th onclick="sortTable(0,'picksViewTable')">Entrant</th>
        <th onclick="sortTable(1,'picksViewTable')">Conference</th>
        <th onclick="sortTable(2,'picksViewTable')">Round</th>
        <th onclick="sortTable(3,'picksViewTable')">Team</th>
        <th onclick="sortTable(4,'picksViewTable')">Round Points</th>
        <th onclick="sortTable(5,'picksViewTable')">Upset Points</th>
        <th onclick="sortTable(6,'picksViewTable')">Total Points</th>
        <th onclick="sortTable(7,'picksViewTable')">Result</th>
    </tr>
    </thead>
    <tbody>
    <#list picks as pick>
        <tr class="<#if pick.result?has_content && pick.result == 'CORRECT'>correct<#elseif pick.result?has_content && pick.result == 'WRONG'>incorrect</#if>">
            <td>${entrantNames[pick.entrantId?string]}</td>
            <td>${conferenceNames[pick.conferenceId?string]}</td>
            <td>${pick.round}</td>
            <td>${pick.teamName}</td>
            <td class="<#if pick.result?has_content && pick.result == 'WRONG'>strikethrough</#if>">${pick.weightedRoundPoints}</td>
            <td class="<#if pick.result?has_content && pick.result == 'WRONG'>strikethrough</#if>">${pick.weightedUpsetPoints}</td>
            <td class="<#if pick.result?has_content && pick.result == 'WRONG'>strikethrough</#if>">${pick.totalPotentialPoints}</td>
            <td>${pick.result}</td>
        </tr>
    </#list>
    </tbody>
</table>

<#include "footer.ftl">
