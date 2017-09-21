<ul>
<c:forEach items="${menu}" var="item">
    <li>
    <c:if test = "${current_page.title != item.title}">
        <a href="${item.link}">
    </c:if>
    ${item.title}
    <c:if test = "${current_page.title != item.title}">
        </a>
    </c:if>
    <br>
</c:forEach>
</ul>