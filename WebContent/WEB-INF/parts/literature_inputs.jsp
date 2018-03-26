<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                                <div class="form-group">
                                    <input type="text" name="author" id="author" tabindex="1" class="form-control"
                                           placeholder="Autor" required value="<c:out value='${literature.author}'/>">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="title" id="title" tabindex="2"
                                           class="form-control" placeholder="Titel" required value="<c:out value='${literature.title}'/>">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="source" id="source" tabindex="2"
                                           class="form-control" placeholder="Quelle" required value="<c:out value='${literature.source}'/>">
                                </div>
                                <div class="form-group">
 									<label for="formating">Formatierung</label>
  									<select name="formating" class="form-control" id="formating">
										<c:set var="formatings">rechtsbündig,linksbündig,Blocksatz,zentriert</c:set>
										<c:forTokens items="${formatings}" delims="," var="formating">
										    <option ${ literature.formating == formating ? 'selected="selected"' : ''}>${formating}</option>
										</c:forTokens>
  									</select>
								</div>
                                <div class="form-group">
                                    <textarea class="form-control" id="texteingabe" name="content" rows="5" maxlength="5000" placeholder="Texteingabe"><c:out value='${literature.content}'/></textarea>
                                </div>
                                <div class="form-group">
                                    <textarea class="form-control" id="motivation" name="motivation" rows="5" maxlength="500" placeholder="Motivation zum Schreiben/Lesen"><c:out value='${literature.motivation}'/></textarea>
                                </div>
                                <!--
								<div class="form-group">
									<div class="input-group input-file">
    									<input type="text" class="form-control" placeholder="Upload einer PDF (optional, max. 5MB)" />			
            							<span class="input-group-btn">
        									<button class="btn btn-default btn-choose" type="button">Auswählen</button>
    									</span>
									</div>
								</div>
								-->
								<div class="form-group">
 									<label for="topic">Themen</label>
  									<select name="topic" class="form-control" id="topic">
										<c:set var="topics">Liebe und Beziehungskisten,Lachen und Leiden,Glück und Zeit,Traumhaftes,Trauer und Abschied,Was mir heilig ist,Landschaften: innere und äußere,Unsagbares,Über Grenzen</c:set>
										<c:forTokens items="${topics}" delims="," var="topic">
										    <option ${ literature.topic == topic ? 'selected="selected"' : ''}>${topic}</option>
										</c:forTokens>
  									</select>
								</div>
								<div class="form-group">
 									<label for="application">Anwendungsgebiete</label>
  									<select name="application" class="form-control" id="application">
										<c:set var="applications">Erste Hilfe,Trostpflaster,Hausmittel,Wundermittel,Placebo,Injektionen,Balsam,Pro-Thesen</c:set>
										<c:forTokens items="${applications}" delims="," var="application">
										    <option ${ literature.application == application ? 'selected="selected"' : ''}>${application}</option>
										</c:forTokens>
  									</select>
								</div>
								<div class="form-group">
 									<label for="genre">Genres</label>
  									<select name="genre" class="form-control" id="genre">
										<c:set var="genres">Gedichte,Gereimtes,Romane und Erzählungen,Märchen und Mythen,Songtexte,Philosophisches,Gebete,Lebenserinnerungen,Sonstiges</c:set>
										<c:forTokens items="${genres}" delims="," var="genre">
										    <option ${ literature.genre == genre ? 'selected="selected"' : ''}>${genre}</option>
										</c:forTokens>
  									</select>
								</div>
								