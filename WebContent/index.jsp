<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<!--
    Created by Artisteer v3.0.0.32906
    Base template (without user's data) checked by http://validator.w3.org : "This page is valid XHTML 1.0 Transitional"
    -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Fundalara</title>
<link rel="stylesheet" href="Recursos/Estilos/styleWebSite.css"
	type="text/css" media="screen" />
<!--[if IE 6]><link rel="stylesheet" href="style.ie6.css" type="text/css" media="screen" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="style.ie7.css" type="text/css" media="screen" /><![endif]-->

<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="script.js"></script>
</head>
<body>



	<div id="art-page-background-middle-texture">
		<div id="art-main">
			<div class="art-sheet">
				<div class="art-sheet-tl"></div>
				<div class="art-sheet-tr"></div>
				<div class="art-sheet-bl"></div>
				<div class="art-sheet-br"></div>
				<div class="art-sheet-tc"></div>
				<div class="art-sheet-bc"></div>
				<div class="art-sheet-cl"></div>
				<div class="art-sheet-cr"></div>
				<div class="art-sheet-cc"></div>
				<div class="art-sheet-body">
					<div class="art-header">
						<div class="art-header-center">
							<div class="art-header-png"></div>
							<div class="art-header-jpeg" align="center">
								<img src="Recursos/Imagenes/bn.gif" alt="an image"
									style="float: center; border: 0;">
							</div>
						</div>

					</div>
					<div class="art-nav">
						<div class="l"></div>
						<div class="r"></div>
						<div class="art-nav-center">
							<ul class="art-menu">
								<li><a class="active" href="#"><span class="l"></span><span
										class="r"></span><span class="t">Inicio</span></a></li>

								<li><a class="active" href="#"><span class="l"></span><span
										class="r"></span><span class="t">¿Quiénes somos?</span></a>
									<ul>

										<li><a href="#">Misión</a></li>
										<li><a href="#">Visión</a></li>
										<li><a href="#">Compromisos</a></li>
										<li><a href="#">Valores</a></li>
										<li><a href="#">Organigrama</a></li>
										<li><a href="#">Contáctanos</a></li>

									</ul></li>



								<li><a href="pageCategorias.jsp"><span class="l"></span><span
										class="r"></span><span class="t">Categorías</span></a></li>
								<li class="art-menu-separator"></span></span></li>

								<li><a href="#"><span class="l"></span><span class="r"></span><span
										class="t">Galería</span></a>
									<ul>

										<li><a href="#">Fotos</a></li>
										<li><a href="#">Videos</a></li>
									</ul></li>
								<li></li>
								<li=""><a href="#"><span class="l"></span><span
										class="r"></span><span class="t">¿Qué necesito?</span></a>
									<ul>
										<li><a href="#">¿Cómo participar?</a></li>
										<li><a href="#">Formatos</a></li>

									</ul></li>
							</ul>
						</div>
					</div>
					<div class="art-content-layout">
						<div class="art-content-layout-row">
							<div class="art-layout-cell art-sidebar1">


								<div class="art-layout-bg"></div>
								<div class="art-layout-glare">
									<div class="art-layout-glare-image"></div>


								</div>
								<div class="art-block">
									<div class="art-block-body">

										<div class="art-blockheader">
											<div class="l"></div>
											<div class="r"></div>
											<h3 class="t">Eventos</h3>

										</div>

										<img src="Recursos/Imagenes/calendario.png"> <br> <br>
													<div class="art-blockheader">
														<div class="l"></div>
														<div class="r"></div>
														<h3 class="t">Estadísticas</h3>

													</div> <img src="Recursos/Imagenes/grafico.jpg"> <br>
															<br>
																<div class="art-blockheader">
																	<div class="l"></div>
																	<div class="r"></div>
																	<h3 class="t">Encuesta</h3>

																</div>

																<div class="art-blockcontent">

																	<div class="art-blockcontent-body">
																		<form name="form2" method="post" action="index.php">
																			<table class="poll" align="center" border="0"
																				cellpadding="1" cellspacing="0" width="95%">
																				<thead>
																					<tr>
																						<td style="font-weight: bold;">¿En qué
																							categoría desea inscribir a su hijo?</td>
																					</tr>
																				</thead>
																				<tbody>
																					<tr>
																						<td align="center">
																							<table class="pollstableborder" border="0"
																								cellpadding="0" cellspacing="0">
																								<tbody>
																									<tr>
																										<td class="sectiontableentry2" valign="top">
																											<input alt="1" value="1" id="voteid1"
																											name="voteid" type="radio">
																										</td>
																										<td class="sectiontableentry2" valign="top">
																											<label for="voteid1">Preparatorio Niv
																												I</label>
																										</td>
																									</tr>
																									<tr>
																										<td class="sectiontableentry1" valign="top">
																											<input alt="2" value="2" id="voteid2"
																											name="voteid" type="radio">
																										</td>
																										<td class="sectiontableentry1" valign="top">
																											<label for="voteid2">Preparatorio Niv
																												II</label>
																										</td>
																									</tr>
																									<tr>
																										<td class="sectiontableentry2" valign="top">
																											<input alt="3" value="3" id="voteid3"
																											name="voteid" type="radio">
																										</td>
																										<td class="sectiontableentry2" valign="top">
																											<label for="voteid3">PreInfantil</label>
																										</td>
																									</tr>
																									<tr>
																										<td class="sectiontableentry1" valign="top">
																											<input alt="4" value="4" id="voteid4"
																											name="voteid" type="radio">
																										</td>
																										<td class="sectiontableentry1" valign="top">
																											<label for="voteid4">Infantil</label>
																										</td>
																									</tr>
																									<tr>
																										<td class="sectiontableentry2" valign="top">
																											<input alt="5" value="5" id="voteid5"
																											name="voteid" type="radio">
																										</td>
																										<td class="sectiontableentry2" valign="top">
																											<label for="voteid5">PreJunior</label>
																										</td>
																									</tr>
																									<tr>
																										<td class="sectiontableentry1" valign="top">
																											<input alt="6" value="6" id="voteid6"
																											name="voteid" type="radio">
																										</td>
																										<td class="sectiontableentry1" valign="top">
																											<label for="voteid6">Junior</label>
																										</td>
																									</tr>
																									<tr>
																										<td class="sectiontableentry2" valign="top">
																											<input alt="7" value="7" id="voteid7"
																											name="voteid" type="radio">
																										</td>
																										<td class="sectiontableentry2" valign="top">
																											<label for="voteid7">Juvenil</label>
																										</td>
																									</tr>
																								</tbody>
																							</table>
																						</td>
																					</tr>
																					<tr>
																						<td>
																							<div align="center">
																								<span class="art-button-wrapper"> <span
																									class="art-button-l"> </span> <span
																									class="art-button-r"> </span> <input
																									value="Votar" class="art-button"
																									name="task_button" type="submit"></span>&nbsp;
																								<span class="art-button-wrapper"> <span
																									class="art-button-l"> </span> <span
																									class="art-button-r"> </span> <input
																									value="Resultados" class="art-button"
																									name="option" type="button"></span>
																							</div>
																						</td>
																					</tr>
																				</tbody>
																			</table>
																		</form>

																		<div class="cleared"></div>
																	</div>
																</div>
																<div class="cleared"></div>
									</div>
								</div>
								<div class="art-block">
									<div class="art-block-body">
										<div class="art-blockheader">
											<div class="l"></div>
											<div class="r"></div>
											<h3 class="t">Enlaces</h3>
											<img src="Recursos/Imagenes/enlaces.png" align="middle">
										</div>
										<div class="art-blockcontent">
											<div class="art-blockcontent-body">


												<div class="cleared"></div>
											</div>
										</div>
										<div class="cleared"></div>
									</div>
								</div>
								<div class="cleared"></div>
							</div>
							<div class="art-layout-cell art-content">
								<div class="art-post">
									<div class="art-post-body">
										<div class="art-post-inner art-article">
											<h2 class="art-postheader">
												<img src="Recursos/Imagenes/postheadericon.png" alt=""
													height="22" width="22">Inicia la Temporada 
											</h2>
											<div class="art-postheadericons art-metadata-icons">
												<img src="Recursos/Imagenes/postdateicon.png" alt=""
													height="18" width="17"> 13 de febrero de 2012 | <img
													src="Recursos/Imagenes/postpdficon.png" alt="" height="18"
													width="18"> | <img
														src="Recursos/Imagenes/postprinticon.png" alt=""
														height="20" width="23"> | <img
															src="Recursos/Imagenes/postemailicon.png" alt=""
															height="10" width="15">
											</div>
											<div class="art-postcontent">
												<img class="art-article" src="Recursos/Imagenes/ninos.jpg"
													alt="an image" style="float: left;">
													<p>
														En horas de la mañana de ayer, en companía de
														representantes del Instituto de Deportes del Estado Lara,
														la Organizacion Nacional Antidrogas y la Gobernacion del
														estado Lara; la Liga de Beisbol Menor Fundalara dijo Play
														Ball a la Temporada 2011-2012, copa Francisco Rangel Gomez
														y Prevencion del Delito, con participacion de diez
														escuelas beisboleras.
														<p>
															<span class="art-button-wrapper"> <span
																class="art-button-l"> </span> <span class="art-button-r">
															</span> <a class="readon art-button" href="javascript:void(0)">Leer&nbsp;más...</a>
															</span>
														</p>

														<br><br><br> <img
																	src="Recursos/Imagenes/publicidad.png"><br><br>
													</p>
											</div>
											<div class="cleared"></div>
										</div>


										<div class="cleared"></div>
									</div>
								</div>
								<div class="art-post"></div>
								<div class="cleared"></div>
							</div>
							<div class="art-layout-cell art-sidebar2">
								<div class="art-layout-bg"></div>
								<div class="art-layout-glare">
									<div class="art-layout-glare-image"></div>
								</div>
								<div class="art-block">
									<div class="art-block-body">
										<div class="art-blockheader">
											<div class="l"></div>
											<div class="r"></div>
											<h3 class="t">Acceder</h3>
										</div>
										<div class="art-blockcontent">
											<div class="art-blockcontent-body">
												<form id="form-login" name="login" method="post" action="olimpo.jsp">
													<fieldset class="input">
														<span class="art-button-wrapper"> <span
															class="art-button-l"> </span> <span class="art-button-r">
														</span><input name="Submit" class="art-button" value="Ingresar"
															type="submit" />
														</span>
													</fieldset>
													<ul>
														<li><a href="#"> ¿Olvidó su Usuario y/o
																Contraseña?</a></li>
													</ul>
												</form>
												<div class="cleared"></div>
											</div>
										</div>
										<div class="cleared"></div>
									</div>
								</div>
								<div class="art-block">
									<div class="art-block-body">
										<div class="art-blockheader">
											<div class="l"></div>
											<div class="r"></div>
											<h3 class="t">Noticias Recientes</h3>
											<div class="art-blockcontent-body">
												<div class="bannergroup_text">
													<img src="Recursos/Imagenes/noticias.png"><br><br>
																<img src="Recursos/Imagenes/iconos.png"><br><br>
<!-- 																			<img src="Recursos/Imagenes/redes.png"> -->
<script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js"></script>
<script>
new TWTR.Widget({
  version: 2,
  type: 'profile',
  rpp: 5,
  interval: 30000,
  width: 200,
  height: 300,
  theme: {
    shell: {
      background: '#d69545',
      color: '#ffffff'
    },
    tweets: {
      background: '#ffffff',
      color: '#472d47',
      links: '#c77c20'
    }
  },
  features: {
    scrollbar: false,
    loop: false,
    live: true,
    behavior: 'default'
  }
}).render().setUser('fundabeisbol').start();
</script>
													<div class="art-blockcontent-body"></div>
												</div>
												<div class="cleared"></div>
											</div>
										</div>
										<div class="art-blockcontent"></div>
										<div class="cleared"></div>
									</div>
								</div>
								<div class="cleared"></div>
							</div>
						</div>
					</div>
					<div class="cleared"></div>
					<div class="art-footer">
						<div class="art-footer-t"></div>
						<div class="art-footer-l"></div>
						<div class="art-footer-b"></div>
						<div class="art-footer-r"></div>
						<div class="art-footer-body">

							<div class="art-footer-text">
								<p>2012 E.B.M. Fundalara. Todos los derechos reservados.</p>
							</div>
							<div class="cleared"></div>
						</div>
					</div>
					<div class="cleared"></div>
				</div>
			</div>
			<div class="cleared"></div>
		</div>
	</div>
</body>
</html>