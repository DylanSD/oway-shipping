@param gg.jte.Content content

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Oway Shipping</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/htmx.org@2.0.7/dist/htmx.min.js"></script>
<%--    <script type="text/javascript" th:src="@{/webjars/htmx.org/dist/htmx.min.js}"></script>--%>
<%--    <script src="/webjars/htmx.org/2.0.7/htmx.min.js"></script>--%>
    <script src="https://cdn.jsdelivr.net/npm/htmx-ext-sse@2.2.4" integrity="sha384-A986SAtodyH8eg8x8irJnYUk7i9inVQqYigD6qZ9evobksGNIXfeFvDwLSHcp31N" crossorigin="anonymous"></script>
</head>
<body class="bg-gray-100">
${content}
</body>
</html>