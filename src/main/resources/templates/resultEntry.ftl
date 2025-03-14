<#include "header.ftl">
<h2>Game Result Entry</h2>
<form action="/resultEntry" method="post">
    <div class="form-group">
        <label for="conferenceId">Conference</label>
        <select id="conferenceId" name="conferenceId">
            <#list conferenceOptions as conference>
                <option value="${conference.conferenceId}">${conference.conferenceName}</option>
            </#list>
        </select>
    </div>
    <table id="resultsTable">
        <thead>
        <tr>
            <th>Round</th>
            <th>Winning Team</th>
            <th>Losing Team</th>
            <th>Game Date</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody id="resultsTableBody">
        <!-- Rows dynamically added by JavaScript -->
        </tbody>
    </table>
    <button type="button" class="btn" onclick="addRow()">Add Row</button>
    <button type="submit" class="btn">Submit</button>
</form>

<script>
    function removeRow(btn) {
        const row = btn.closest('tr');
        row.remove();
    }

    function addRow() {
        const tableBody = document.getElementById("resultsTableBody");
        const newRow = document.createElement("tr");
        newRow.innerHTML = `
                    <td>
                        <select name="round[]">
                            <#list roundOptions as option>
                                <option value="${option}">${option.description}</option>
                            </#list>
                        </select>
                    </td>
                    <td>
                        <input type="text" name="winningTeamName[]" required>
                    </td>
                    <td>
                        <input type="text" name="losingTeamName[]" required>
                    </td>
                    <td>
                        <input type="date" id="gameDate" name="gameDate[]" required value="${today}">
                    </td>
                    <td>
                        <button type="button" class="btn" onclick="removeRow(this)">Remove</button>
                    </td>
                `;
        tableBody.appendChild(newRow);
    }
</script>
<#include "footer.ftl">
