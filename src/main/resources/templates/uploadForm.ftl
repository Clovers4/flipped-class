<!-- TODO : just for upload test-->
<html lang="zh-CN">
<head>
</head>
<body>

<div #if="${message!}">
    <h2>${message!}</h2>
</div>

<div>
    <form method="POST" enctype="multipart/form-data" action="/multipart/">
        <table>
            <tr>
                <td>File to upload:</td>
                <td><input type="file" name="file"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Upload"/></td>
            </tr>
        </table>
    </form>
</div>

<div>
    <ul>
        <#list files as file>
            <li>
                <a href="${file}">${file}</a>
            </li>
        </#list>
    </ul>
</div>

</body>
</html>