<#include "header.ftl">
<h2>Pick Entry</h2>

<script>
    var roundOptions = JSON.parse("${roundOptionsJson?js_string}");
    var conferenceOptions = JSON.parse("${conferenceOptionsJson?js_string}");
</script>

<form action="/pickEntry" method="post">
    <div class="form-group">
        <label for="entrantId">Entrant</label>
        <select id="entrantId" name="entrantId">
            <#list entrantOptions as entrant>
                <option value="${entrant.entrantId}">${entrant.nickname}</option>
            </#list>
        </select>
    </div>
    <table id="picksTable">
        <thead>
        <tr>
            <th>Conference</th>
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
    <button type="button" class="btn" onclick="openUploadModal()">Import Picks</button>
    <button type="button" class="btn" onclick="addRow()">Add Row</button>
    <button type="submit" class="btn">Submit</button>
</form>

<!-- Modal for file upload -->
<div id="uploadModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeUploadModal()">&times;</span>
        <h3>Import Picks</h3>
        <p>Please select an Excel file (.xls or .xlsx).</p>
        <form id="uploadForm" onsubmit="loadExcelPicks(event); return false;" enctype="multipart/form-data">
            <div class="form-group">
                <label for="excelFile">Excel File:</label>
                <input type="file" id="excelFile" name="picksFile" accept=".xls,.xlsx" required>
            </div>
            <button type="submit" class="btn">Upload</button>
        </form>
    </div>
</div>

<script>
    // Generate HTML for conference and round options using standard string concatenation.
    function generateConferenceOptions(selected) {
        var optionsHtml = "";
        conferenceOptions.forEach(function (opt) {
            var isSelected = (opt === selected) ? " selected" : "";
            optionsHtml += '<option value="' + opt + '"' + isSelected + '>' + opt + '</option>';
        });
        return optionsHtml;
    }

    function generateRoundOptions(selected) {
        var optionsHtml = "";
        roundOptions.forEach(function (opt) {
            var isSelected = (opt === selected) ? " selected" : "";
            optionsHtml += '<option value="' + opt + '"' + isSelected + '>' + opt + '</option>';
        });
        return optionsHtml;
    }

    // Adds a new row to the picks table. If parameters are provided, prefill them.
    function addRow(conference, round, teamName, upsetPoints) {
        if (arguments.length === 0) {
            conference = "";
            round = "";
            teamName = "";
            upsetPoints = 0;
        }
        var tableBody = document.getElementById("picksTableBody");
        var newRow = document.createElement("tr");
        newRow.innerHTML =
            '<td><select name="conference[]">' + generateConferenceOptions(conference) + '</select></td>' +
            '<td><select name="round[]">' + generateRoundOptions(round) + '</select></td>' +
            '<td><input type="text" name="teamName[]" value="' + teamName + '" required></td>' +
            '<td><input type="number" name="upsetPoints[]" value="' + upsetPoints + '" min="0" required></td>' +
            '<td><button type="button" class="btn" onclick="removeRow(this)">Remove</button></td>';
        tableBody.appendChild(newRow);
    }

    function removeRow(btn) {
        var row = btn.closest('tr');
        row.remove();
    }

    // Open the modal
    function openUploadModal() {
        document.getElementById("uploadModal").style.display = "block";
    }

    // Close the modal
    function closeUploadModal() {
        document.getElementById("uploadModal").style.display = "none";
    }

    // Handle Excel file upload, parse via AJAX, and fill the table with returned rows.
    async function loadExcelPicks(event) {
        event.preventDefault();
        var fileInput = document.getElementById("excelFile");
        if (fileInput.files.length === 0) {
            alert("Please select an Excel file.");
            return;
        }
        var file = fileInput.files[0];
        var formData = new FormData();
        formData.append("picksFile", file);

        try {
            var response = await fetch("/parsePicksExcel", {
                method: "POST",
                body: formData
            });
            if (!response.ok) {
                alert("Upload Error: " + response.statusText);
            }
            var data = await response.json();
            // Expected data: array of objects with properties: round, teamName, upsetPoints.
            document.getElementById("picksTableBody").innerHTML = "";
            data.forEach(function (pick) {
                addRow(pick.conference, pick.round, pick.teamName, pick.upsetPoints);
            });
            closeUploadModal();
        } catch (err) {
            console.error(err);
            alert("Upload Error: " + err.message);
        }
    }
</script>

<#include "footer.ftl">
