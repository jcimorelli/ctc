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
        </div>
        <button type="submit" class="btn">Show</button>
    </form>
</div>

<table id="picksViewTable">
    <thead>
    <tr>
        <th>Entrant</th>
        <th>Round</th>
        <th>Team Name</th>
        <th>Upset Points</th>
        <th>Result</th>
        <th>Submitted Time</th>
    </tr>
    </thead>
    <tbody>
    <#list picks as pick>
        <tr class="<#if pick.result?has_content && pick.result == 'CORRECT'>correct<#elseif pick.result?has_content && pick.result == 'INCORRECT'>incorrect</#if>">
            <td>${entrantNames[pick.entrantId?string]}</td>
            <td>${pick.round}</td>
            <td>${pick.teamName}</td>
            <td>${pick.upsetPoints}</td>
            <td>${pick.result}</td>
            <td>${pick.submittedTime}</td>
        </tr>
    </#list>
    </tbody>
</table>

<#include "footer.ftl">
