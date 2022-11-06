 <%@page import="java.util.ArrayList"%>
<%@page import="enregistrement.Enregistrement"%>
<%-- 
    Document   : homeJSP
    Created on : 15 sept. 2022, 15:43:30
    Author     : BABA SAIDOU DIEME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agenda</title>x
    <link rel="stylesheet" href="WEB-CONTENT/css/style.css">
    <link rel="stylesheet" href="WEB-CONTENT/css/cal_style.css">
  
</head>
 
<body>
    <%@page import="MONSERVLET.homeServlet" %> 
    <%
        ArrayList<Enregistrement> record = (ArrayList<Enregistrement>) request.getAttribute("listRecord");
    %>
    <div class="allContainer flex">
        <div class="leftSide">
            <div class="imgContainer flex">
                <img src="WEB-CONTENT/images/logo.png" alt="Logo">
            </div>

            <form class="form_agenda" action="test" method="post">
                <div class="inputs_container">
                    <div class="input_left">
                        <label for="date">Date début</label>
                        <input type="date" name="date" id="date" required>
                    </div>
                    <div class="input_right">
                        <label for="time">Heure début</label>
                        <input type="time" name="time" id="time" required>
                    </div>
                </div>
                <div class="inputs_container">
                    <div class="input_left">
                        <label for="date">Date fin</label>
                        <input type="date" name="dateEnd" id="dateEnd" required>
                    </div>
                    <div class="input_right">
                        <label for="time">Heure fin</label>
                        <input type="time" name="timeEnd" id="timeEnd" required>
                    </div>
                </div>
                <label for="libelle">Libellé</label>
                <input type="text" name="libelle" id="libelle" placeholder="Libellé de l'évenement" required>
                <select name="categorie" id="typeEv">
                    <option value="">Selectionnez lee type d'événement</option>
                    <option value="Réligion">Réligion</option>
                    <option value="Famille">Famille</option>
                    <option value="Etude">Etude</option>
                    <option value="Travail">Travail</option>
                </select>
                <label for="participants">Participants</label>
                <input type="text" name="participants" id="participants" placeholder="Mettez les participants séparés par vigule">
                <label for="time">Que voulez-vous noter ?</label>
                <textarea name="contenu" placeholder="Mettez ici le contenu" id="contenu" cols="30" rows="5"></textarea>
                <button type="submit">Ajouter événement</button>
            </form>

        </div>
        <div class="middleSide">
            <h2>
                Événements à venir
            </h2><br>
             <div class="filter_container">
                <button id="parJour" class="filer active" onclick="switchActiveEvents(this.id)">Par jour</button>
                <button id="parSemaine" class="filer" onclick="switchActiveEvents(this.id)">Par semaine</button>
                <button id="parMois" class="filer" onclick="switchActiveEvents(this.id)">Par mois</button>
            </div>
            <div id="id_parJour" class="eventsToCome">
                
                <%
                        for(int counter=0; counter<record.size(); counter++){
                    
                    %>
                    <div class="event2 flex">
                    <p class="date">
                        <%
                            out.print(record.get(counter).getDate());
                        %>
                    </p>
                    <div class="allEnventContainer">
                        <div class="eventContainer">
                            <div class="time">
                                <span class="typeEvent">
                                    <%
                                    out.print(record.get(counter).getCategorie());
                                    %>
                                </span>
                                </span>
                                <span>
                                    <%
                                    out.print(record.get(counter).getLibelle());
                                    %>
                                </span>
                                <br><span class="date_gris">
                                    <%
                                    out.print(record.get(counter).getDate()+" à "+record.get(counter).getHeure());
                                    %>
                                </span>à<span class="date_gris">
                                    <%
                                    out.print(record.get(counter).getDateFin()+" à "+record.get(counter).getHeureFin());
                                    %>
                                </span>
                            </div>
                            <div class="details">
                               <%
                                    out.print(record.get(counter).getContenu());
                                %>
                            </div>
                            <div class="participants_contanier">
                                <p class="participant">
                                    <%
                                    out.print(record.get(counter).getParticipant());
                                    %>
                                </p>
                       
                            </div>
                            <a href="deleteEvents?id=<%
                                    out.print(record.get(counter).getId());
                                    %>"> 
                                <img src="WEB-CONTENT/images/trash.png" alt="" srcset=""> 
                            </a>    
                            
                        </div>
                    </div>
                  </div>
                   <%}%>
           
            </div>
            <div id="parSemaine" class="eventsToCome d_none">
                <div class="event2 flex">
                    <p class="date">
                        Semaine N
                    </p>
                    <div class="allEnventContainer">
                        <div class="eventContainer">
                            <div class="time">
                                <span>Le libellé par ici</span>
                                <br><span class="date_gris">12/08/22 20h34</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                        <div class="eventContainer">
                            <div class="time">
                                <span>Le libellé par ici</span>
                                <br><span class="date_gris">12/08/22 20h34</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                        <div class="eventContainer">
                            <div class="time">
                                <span>Le libellé par ici</span>
                                <br><span class="date_gris">12/08/22 20h34</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                    </div>
                </div>
                <div class="event2 flex">
                    <p class="date">
                        Semaine N
                    </p>
                    <div class="allEnventContainer">
                        <div class="eventContainer">
                            <div class="time">
                                <span>Le libellé par ici</span>
                                <br><span class="date_gris">12/08/22 20h34</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                        <div class="eventContainer">
                            <div class="time">
                                <span>Le libellé par ici</span>
                                <br><span class="date_gris">12/08/22 20h34</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                    </div>
                </div>
                <div class="event2 flex">
                    <p class="date">
                        Semaine N
                    </p>
                    <div class="allEnventContainer">
                        <div class="eventContainer">
                            <div class="time">
                                <span>Le libellé par ici</span>
                                <br><span class="date_gris">12/08/22 20h34</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                        <div class="eventContainer">
                            <div class="time">
                                <span>Le libellé par ici</span>
                                <br><span class="date_gris">12/08/22 20h34</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="parMois" class="eventsToCome d_none">
                <div class="event2 flex">
                    <p class="date">
                        Le mois ici
                    </p>
                    <div class="allEnventContainer">
                        <div class="eventContainer">
                            <div class="time">
                                Mardi 20 : <span>Le libellé par ici</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                        <div class="eventContainer">
                            <div class="time">
                                Mardi 20 : <span>Le libellé par ici</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                        <div class="eventContainer">
                            <div class="time">
                                Mardi 20 : <span>Le libellé par ici</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                    </div>
                </div>
                <div class="event2 flex">
                    <p class="date">
                        Le mois ici
                    </p>
                    <div class="allEnventContainer">
                        <div class="eventContainer">
                            <div class="time">
                                Lunid 21: <span>Le libellé par ici</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                        <div class="eventContainer">
                            <div class="time">
                                Lunid 21: <span>Le libellé par ici</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                    </div>
                </div>
                <div class="event2 flex">
                    <p class="date">
                        Le mois ici
                    </p>
                    <div class="allEnventContainer">
                        <div class="eventContainer">
                            <div class="time">
                                Lunid 21: <span>Le libellé par ici</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                        <div class="eventContainer">
                            <div class="time">
                                Lunid 21: <span>Le libellé par ici</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="rightSide">
            <div class="calender_container">
                <div class="calendar"></div>
                <div class="par_date_event_container">
                    <div class="allEnventContainer">
                        <div class="eventContainer">
                            <div class="time">
                                20h34 : <span>Le libellé par ici</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                        <div class="eventContainer">
                            <div class="time">
                                20h34 : <span>Le libellé par ici</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                        <div class="eventContainer">
                            <div class="time">
                                20h34 : <span>Le libellé par ici</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                        <div class="eventContainer">
                            <div class="time">
                                20h34 : <span>Le libellé par ici</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                        <div class="eventContainer">
                            <div class="time">
                                20h34 : <span>Le libellé par ici</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                        <div class="eventContainer">
                            <div class="time">
                                20h34 : <span>Le libellé par ici</span>
                            </div>
                            <div class="details">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio aliquam aperiam illo laudantium.
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- The Modal -->
    <div id="myModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <span class="close">&times;</span>
            <p>Some text in the Modal..</p>
        </div>
    </div>

    </div>
</body>
<script src="WEB-CONTENT/js/script.js"></script>
</html>
