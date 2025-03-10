<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enter Your Pick</title>
    <link rel="stylesheet" href="/css/styles.css">
    <script>
        // Function to remove a row when the "Remove" button is clicked
        function removeRow(btn) {
            var row = btn.parentNode.parentNode;
            row.parentNode.removeChild(row);
        }

        // Function to add a new row by cloning the template
        function addRow() {
            var template = document.getElementById("row-template").innerHTML;
            // Append the row HTML to the tbody of the table
            var tableBody = document.getElementById("picksTableBody");
            tableBody.insertAdjacentHTML('beforeend', template);
        }
    </script>
</head>
<body>
<h1>Enter Your Pick</h1>
<#include "alerts.ftl">

<form action="/pickEntry" method="post">
    <!-- Conference selection (populated dynamically) -->
    <div>
        <label for="conferenceId">Conference:</label>
        <select id="conferenceId" name="conferenceId">
            <#list conferenceOptions as conference>
                <option value="${conference.conferenceId}">${conference.conferenceName}</option>
            </#list>
        </select>
    </div>

    <!-- Table for pick rows -->
    <table id="picksTable" border="1">
        <thead>
        <tr>
            <th>Round</th>
            <th>Team Name</th>
            <th>Upset Points</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody id="picksTableBody">
        <!-- You can pre-load one row if desired, or start with an empty table -->
        </tbody>
    </table>
    <div>
        <button type="button" onclick="addRow()">Add Row</button>
    </div>
    <div>
        <button type="submit">Submit Picks</button>
    </div>
</form>

<!-- Hidden template for a row. This is not rendered directly -->
<script id="row-template" type="text/template">
    <tr>
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
            <button type="button" onclick="removeRow(this)">Remove</button>
        </td>
    </tr>
</script>
</body>
</html>
