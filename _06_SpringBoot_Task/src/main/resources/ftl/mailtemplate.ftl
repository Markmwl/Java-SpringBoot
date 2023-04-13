<table class="hovertable">
    <thead>
    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>地址</th>
        <th>电话</th>
    </tr>
    </thead>
    <tbody>
     <#list lisUser as user>
    <tr onmouseover="this.style.backgroundColor='#ccffff';" onmouseout="this.style.backgroundColor='#ffffff';">
      <td>${user.id}</td>
      <td>${user.name}</td>
      <td>${user.sex}</td>
      <td>${user.age}</td>
      <td>${user.address}</td>
      <td>${user.phonenumber}</td>
    </tr>
     </#list>
    </tbody>
</table>
    <style type="text/css">
        table.hovertable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            border-width: 1px;
            border-color: #a9c6c9;
            border-collapse: collapse;
        }
        table.hovertable th {
            background-color:#ccccff;
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }
        table.hovertable tr {
            border-color: #a9c6c9;
        }
        table.hovertable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }
    </style>