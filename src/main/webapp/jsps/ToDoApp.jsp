<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ToDo App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        h1 {
            text-align: center;
            color: #007bff;
            margin-top: 30px;
        }
        table {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            border-collapse: collapse;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #dee2e6;
        }
        tr:nth-child(even) {
            background-color: #f8f9fa;
        }
        tr:hover {
            background-color: #e9ecef;
        }
        input[type="text"], input[type="checkbox"] {
            width: calc(100% - 10px);
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ced4da;
            border-radius: 4px;
        }
        .edit-btn, .delete-btn, .update-btn {
            padding: 8px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .edit-btn:hover, .delete-btn:hover, .update-btn:hover {
            background-color: #007bff;
            color: #fff;
        }
        .center {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>ToDo App</h1>
    <form id="todoForm" method="POST" action="/your/api/endpoint">
        <table>
            <thead>
                <tr>
                    <th>S No</th>
                    <th>Work</th>
                    <th>Status</th>
                    <th>Delete/Edit</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td><input type="text" name="work1" value="Task 1" readonly></td>
                    <td><input type="checkbox" name="status1" onclick="toggleStatus(this)"></td>
                    <td>
                        <button type="button" class="delete-btn" onclick="deleteTask(this)"> <a href="/" > Delete </a> </button>
                    </td>
                </tr>
                <!-- Add more rows dynamically here -->
            </tbody>
        </table>
        <div class="center">
            <button type="submit" class="update-btn">Update</button>
        </div>
    </form>

    <script>
        function toggleStatus(checkbox) {
            if (checkbox.checked) {
                checkbox.parentNode.previousElementSibling.firstElementChild.readOnly = true;
            } else {
                checkbox.parentNode.previousElementSibling.firstElementChild.readOnly = false;
            }
        }

        function deleteTask(btn) {
            var row = btn.parentNode.parentNode;
            row.parentNode.removeChild(row);
        }
    </script>
</body>
</html>
