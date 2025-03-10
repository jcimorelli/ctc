<#include "header.ftl">
<h2>Pick Entry</h2>
<form action="/pickEntry" method="post">
    <!-- Conference selection -->
    <div class="form-group">
        <label for="conferenceId">Conference</label>
        <select id="conferenceId" name="conferenceId">
            <#list conferenceOptions as conference>
                <option value="${conference.conferenceId}">${conference.conferenceName}</option>
            </#list>
        </select>
    </div>
    <!-- Table for pick rows -->
    <table id="picksTable">
        <thead>
        <tr>
            <th>Round</th>
            <th>Team Name</th>
            <th>Upset Points</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody id="picksTableBody">
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
        const tableBody = document.getElementById("picksTableBody");
        const newRow = document.createElement("tr");
        newRow.innerHTML = `
                    <td>
                        <select name="round[]">
                            <#list roundOptions as option>
                                <option value="${option}">${option}</option>
                            </#list>
                        </select>
                    </td>
                    <td>
                        <input type="text" name="teamName[]" required>
                    </td>
                    <td>
                        <input type="number" name="upsetPoints[]" min="0" required>
                    </td>
                    <td>
                        <button type="button" class="btn" onclick="removeRow(this)">Remove</button>
                    </td>
                `;
        tableBody.appendChild(newRow);
    }
</script>
<#include "footer.ftl">
