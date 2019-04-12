<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<a href="/main"><button>Back</button></a>
<@c.page>
Add new user

<@l.login "/registration" />

</@c.page>