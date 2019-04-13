<#import "parts/common.ftl" as c>

<@c.page>
List of users

<table>
	<thead>
		<tr>
			<th>Name</th>		<!-- UserName -->
			<th>Role</th>		<!-- Status -->
			<th></th>			<!-- commands -->
		</tr>
	</thead>
	<tbody>
<#list users as user>
	<tr>
		<td>${user.username}</td> <!-- ролей может быть несколько (поэтому выводим список List) -->
		<td><#list user.roles as role>${role}<#sep>, </#list></td>
		<td><a href="/user/${user.id}">edit</a></td>
	</tr>
</#list>
	</tbody>
</table>
</@c.page>