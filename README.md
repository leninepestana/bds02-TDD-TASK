<h2>TAREFA: TDD Event-City</h2>

<h3>TDD Event-City Class Diagram</h3>


![Event-City](https://user-images.githubusercontent.com/22635013/139555001-719be02a-4b22-4777-9640-bf87fe87ff53.jpg)


<h4>💡 Possível estrutura de pastas</h4>

<p>
	<strong>📂C:\ws-bootcamp\02-42.Desafio.TDD.para.entregar\bds02</strong>
</p>

<ul>
	<li>Criação de camadas de acesso a dados - Repositories (JpaRepository)</li>
	<li>Criação de camadas de serviço - Services</li>
	<li>Criação de camadas de controle - Controllers</li>
	<li>DTO</li>
</ul>

<h3>City</h3>
<h4>Métodos implementados</h4>
<ul>
	<li>findAllByName()</li>
	<li>findById(id)</li>
	<li>insert(@RequestBody CityDTO cityDto)</li>
	<li>update(@PathVariable Long id, @RequestBody CityDTO cityDto)</li>
	<li>delete(@PathVariable Long id)</li>
</ul>

<h3>Event</h3>
<h4>Métodos implementados</h4>
<ul>
	<li>findAll()</li>
	<li>findById(@PathVariable Long id)</li>
	<li>insert(@RequestBody EventDTO eventDto)</li>
	<li>update(@PathVariable Long id, @RequestBody EventDTO eventDto)</li>
	<li>delete(@PathVariable Long id)</li>
</ul>

<h3>Testes CityControllerIT</h3>
✅ deleteShouldReturnNotFoundWheNonExistingId()</br>
✅ insertShouldInsertResource()</br>
✅ deleteShouldReturnNoContentWhenIndependentId()</br>
✅ deleteShouldReturnBadRequestWhenDependentId()</br>
✅ findAllShouldReturnAllResourcesSortedByName()</br>
<h3>Testes EventControllerIT</h3>
✅ updateShouldUpdateResourceWhenIdExists()</br>
✅ updateShouldReturnNotFoundWhenIdDoesNotExist()</br>

<hr></</hr>
<p>🥴 Lenine Pestana<br/>
✉ leninepestana@gmail.com
</p>
