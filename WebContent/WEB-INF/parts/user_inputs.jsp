<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

                                <div class="form-group">
                                    <input type="email" name="email" id="email" tabindex="1" class="form-control"
                                           placeholder="E-Mail" value="<c:out value='${user_edit.email}'/>" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" tabindex="2"
                                           class="form-control" placeholder="Passwort (mind. 8 Zeichen)" ${ user_edit == null ? 'required' : '' }>
                                </div>
                                <c:if test="${ user_edit == null }">
                                <div class="form-group">
                                    <input type="password" name="passwordRepeat" id="passwordRepeat" tabindex="2"
                                           class="form-control" placeholder="Passwort wiederholen" required>
                                </div>
                                </c:if>
                                <div class="form-group">
                                    <input type="date" name="birthdate" id="birthdate" tabindex="2"
                                           class="form-control" placeholder="Geburtsdatum" required value="<c:out value='${user_edit.birthdate}'/>">
                                </div>
                               	<div class="form-group">
                                    <input type="text" name="profession" id="profession" tabindex="2"
                                           class="form-control" placeholder="Beruf" value="<c:out value='${user_edit.profession}'/>">
                                </div>
                               	<div class="form-group">
                                    <input type="text" name="land" id="land" tabindex="2"
                                           class="form-control" placeholder="Wo wohnen Sie?" value="<c:out value='${user_edit.land}'/>">
                                </div>
                                <c:if test="${ user_edit == null }">
                                <div class="form-group text-center">
                                    <input type="checkbox" tabindex="3" class="" name="tos" id="tos">
                                    <label for="tos"> <a href="teilnahmebedingungen.html">Teilnahmebedingungen</a> gelesen?</label>
                                </div>
                                </c:if>
                                <div class="form-group text-center">
                                    <input type="checkbox" tabindex="3" class="" name="newsletter" id="newsletter" ${ user_edit.newsletter ? 'checked="checked"' : '' }>
                                    <label for="newsletter"> Newsletter erhalten?</label>
                                </div>
                                