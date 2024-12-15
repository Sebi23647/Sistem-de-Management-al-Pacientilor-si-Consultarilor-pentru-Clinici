Package authentication
  Login
  1. GET /login
  Scop: Afișează formularul de login.
  Parametri: Niciun parametru necesar.
  Răspuns: Returnează o pagină HTML pentru autentificare (authentication/login).

  2. POST /login
  Scop: Permite autentificarea unui utilizator în funcție de rol (Admin, Patient, Doctor).
  Parametri:
  Body: Obiect de tip LoginDTO cu formatul JSON:
  json
  {
      "email": "exemplu@email.com",
      "password": "parola123"
  }
  Comportament:
  Verifică în baza de date dacă utilizatorul există în unul dintre următoarele roluri:
  Admin
  Patient
  Doctor
  Dacă utilizatorul este valid:
  Stochează utilizatorul în sesiune.
  Pentru Doctor, stochează suplimentar specialty și salary în sesiune.
  Redirecționează către /home.
  Dacă autentificarea eșuează:
  Returnează pagina de login cu un mesaj: "Email sau parolă incorecte!".
  Răspuns:
  Succes: Redirecționează către /home.
  Eșec: Returnează pagina authentication/login cu mesaj de eroare.

  3. GET /logout
  Scop: Închide sesiunea utilizatorului autentificat.
  Parametri: Niciun parametru necesar.
  Comportament:
  Invalidează sesiunea curentă a utilizatorului.
  Redirecționează către pagina de login.
  Răspuns: Redirecționează către /login.

  Register
  1. GET /register
  Scop: Afișează formularul de înregistrare pentru utilizatori.
  Parametri: Niciun parametru necesar.
  Răspuns: Returnează pagina HTML pentru înregistrare (authentication/register).

  2. POST /register
  Scop: Permite înregistrarea unui utilizator nou (Admin sau Patient).
  Parametri:
  Body: Obiect de tip RegisterDTO cu formatul JSON:
  json
  {
      "nume": "Popescu",
      "prenume": "Ion",
      "email": "ion.popescu@email.com",
      "password": "parola123",
      "phoneNumber": "0712345678",
      "codAdmin": "4321"
  }
  Comportament:
  Dacă codAdmin corespunde valorii predefinite ("4321"):
  Creează și salvează un Admin nou în baza de date.
  Returnează mesajul "Admin înregistrat cu succes!".
  Dacă codAdmin este incorect sau necompletat:
  Creează și salvează un Patient nou în baza de date.
  Returnează mesajul "Pacient înregistrat cu succes!".
  Parola este salvată direct (Notă: se recomandă criptarea parolei).
  Răspuns:
  Succes: Redirecționează către pagina de login /login.

Package entities Admin
  1. GET /adminPages/addDoctor
  Scop: Afișează pagina pentru adăugarea unui doctor nou.
  Parametri: Niciun parametru necesar.
  Răspuns: Returnează pagina HTML adminPages/addDoctor.

  2. POST /addDoctor
  Scop: Permite adăugarea unui doctor nou în sistem.
  Parametri:
  Body: Obiect de tip Doctor cu formatul JSON:
  json
  {
      "nume": "Ionescu",
      "prenume": "Mihai",
      "email": "ion.popescu@email.com",
      "password": "parola123",
      "phoneNumber": "0712345678",
      "specialty": "Cardiologie",
      "salary": 10000
  }
  Comportament:
  Salvează datele doctorului în baza de date.
  Returnează mesajul "Doctorul a fost adăugat cu succes!".
  Răspuns: Returnează pagina adminPages/addDoctor cu un mesaj de succes.


  3. GET /adminPages/searchDoctor
  Scop: Afișează pagina pentru căutarea doctorilor.
  Parametri: Niciun parametru necesar.
  Răspuns: Returnează pagina adminPages/searchDoctor.

  4. POST /searchDoctor
  Scop: Permite căutarea doctorilor după nume.
  Parametri:
  Body: Parametru nume sub formă de String.
  Comportament:
  Caută doctorii în baza de date folosind numele furnizat.
  Afișează lista doctorilor găsiți.
  Răspuns: Returnează pagina adminPages/searchDoctor cu doctorii găsiți.

  5. GET /doctorDetails/{id}
  Scop: Afișează detaliile unui doctor specific.
  Parametri:
  Path Variable: id (ID-ul doctorului).
  Comportament:
  Găsește doctorul în baza de date după ID.
  Afișează informațiile acestuia.
  Răspuns: Returnează pagina adminPages/doctorDetails cu detaliile doctorului.
  
  6. GET /adminPages/appointmentsHistory
  Scop: Afișează pagina pentru istoricul programărilor.
  Parametri: Niciun parametru necesar.
  Răspuns: Returnează pagina adminPages/appointmentsHistory.
  
  7. GET /appointmentsHistory
  Scop: Afișează lista tuturor programărilor din sistem.
  Parametri: Niciun parametru necesar.
  Comportament:
  Găsește toate programările din baza de date.
  Afișează lista acestora.
  Răspuns: Returnează pagina adminPages/appointmentsHistory cu lista programărilor.
  
  8. GET /adminPages/treatmentsHistory
  Scop: Afișează pagina pentru istoricul tratamentelor.
  Parametri: Niciun parametru necesar.
  Răspuns: Returnează pagina adminPages/treatmentsHistory.
  
  9. GET /treatmentsHistory
  Scop: Afișează lista tuturor tratamentelor din sistem.
  Parametri: Niciun parametru necesar.
  Comportament:
  Găsește toate tratamentele din baza de date.
  Afișează lista acestora.
  Răspuns: Returnează pagina adminPages/treatmentsHistory cu lista tratamentelor.
  
  10. GET /deleteDoctor/{id}
  Scop: Permite ștergerea unui doctor din sistem.
  Parametri:
  Path Variable: id (ID-ul doctorului).
  Comportament:
  Șterge doctorul din baza de date folosind ID-ul furnizat.
  Returnează mesajul "Doctorul a fost șters cu succes!".
  Răspuns: Redirecționează către pagina /adminPages/searchDoctor.

Package entities Doctor
  
  1. GET /doctorPages/addAppointment
  Scop: Afișează formularul pentru adăugarea unei noi programări.
  Parametri: Niciun parametru necesar.
  Comportament:
  Returnează lista pacienților și doctorilor pentru selectare.
  Răspuns: Returnează pagina doctorPages/addAppointment.
  
  2. POST /doctorPages/addAppointment
  Scop: Permite adăugarea unei noi programări.
  Parametri:
  patientId (Long) - ID-ul pacientului.
  doctorId (Long) - ID-ul doctorului.
  appointmentDate (String) - Data programării.
  status (String) - Statusul programării.
  Comportament:
  Creează o programare nouă asociată pacientului și doctorului.
  Salvează programarea în baza de date.
  Răspuns: Redirecționează către pagina /doctorPages/viewAppointment?id={doctorId}.
  
  3. GET /doctorPages/viewAppointment
  Scop: Afișează lista programărilor asociate unui doctor specific.
  Parametri:
  Query Param: id (Long) - ID-ul doctorului.
  Comportament:
  Găsește doctorul și afișează programările asociate acestuia.
  Răspuns: Returnează pagina doctorPages/viewAppointment cu lista programărilor.
  
  4. GET /doctorPages/deleteAppointment
  Scop: Șterge o programare din sistem.
  Parametri:
  Query Param: appointmentId (Long) - ID-ul programării de șters.
  Query Param: doctorId (Long) - ID-ul doctorului pentru redirecționare.
  Comportament:
  Șterge programarea specificată din baza de date.
  Răspuns: Redirecționează către pagina /doctorPages/viewAppointment?id={doctorId}.
  
  5. GET /doctorPages/editAppointment
  Scop: Afișează formularul pentru editarea unei programări existente.
  Parametri:
  Query Param: appointmentId (Long) - ID-ul programării de editat.
  Comportament:
  Găsește programarea după ID.
  Returnează lista pacienților și doctorilor pentru selectare.
  Răspuns: Returnează pagina doctorPages/editAppointment.
  Dacă programarea nu este găsită, returnează errorPage.

  6. POST /doctorPages/editAppointment
  Scop: Permite actualizarea unei programări existente.
  Parametri:
  appointmentId (Long) - ID-ul programării.
  patientId (Long) - ID-ul pacientului.
  doctorId (Long) - ID-ul doctorului.
  appointmentDate (String) - Data programării.
  status (String) - Statusul programării.
  Comportament:
  Actualizează programarea în baza de date cu noile valori.
  Răspuns: Redirecționează către pagina /doctorPages/viewAppointment?id={doctorId}.
  
  7. GET /doctorPages/assignTreatment
  Scop: Afișează formularul pentru atribuirea unui tratament unei programări.
  Parametri:
  Query Param: appointmentId (Long) - ID-ul programării.
  Comportament:
  Găsește programarea asociată după ID.
  Răspuns: Returnează pagina doctorPages/assignTreatment.
  Dacă programarea nu este găsită, returnează errorPage.
  
  8. POST /doctorPages/assignTreatment
  Scop: Permite atribuirea și salvarea unui tratament asociat unei programări.
  Parametri:
  appointmentId (Long) - ID-ul programării.
  description (String) - Descrierea tratamentului.
  cost (double) - Costul tratamentului.
  period (String) - Perioada tratamentului.
  diagnosis (String) - Diagnosticul pacientului.
  Comportament:
  Creează un tratament nou și îl asociază programării.
  Salvează tratamentul și actualizează programarea.
  Răspuns: Redirecționează către pagina /doctorPages/viewAppointment?id={doctorId}.

Package entities Doctor
  1. GET /patientPages/appointmentTreatment/{id}
  Scop: Afișează tratamentul asociat unei programări specificate.
  Parametri:
  id (Path Variable, Long) - ID-ul programării.
  Comportament:
  Găsește programarea după ID.
  Verifică dacă există un tratament asociat programării.
  Dacă tratamentul există, îl trimite către pagină pentru afișare.
  În caz contrar, returnează o eroare și redirecționează către pagina de vizualizare a programărilor.
  Răspuns:
  Dacă tratamentul există: returnează pagina patientPages/appointmentTreatment.
  Dacă tratamentul lipsește: returnează pagina patientPages/viewAppointments cu mesajul de eroare.

  2. GET /patientPages/viewAppointments
  Scop: Afișează lista programărilor unui pacient specificat.
  Parametri:
  Query Param: patientId (Long) - ID-ul pacientului.
  Comportament:
  Găsește toate programările din baza de date.
  Filtrează programările asociate pacientului respectiv.
  Adaugă lista programărilor în model pentru afișare.
  Răspuns: Returnează pagina patientPages/viewAppointments cu lista programărilor filtrate.
  
  3. GET /patientPages/viewTreatments
  Scop: Afișează lista tratamentelor asociate unui pacient.
  Parametri:
  Query Param: patientId (Long) - ID-ul pacientului.
  Comportament:
  Găsește toate programările din baza de date.
  Filtrează programările asociate pacientului respectiv.
  Extrage tratamentele asociate programărilor (dacă există).
  Adaugă lista tratamentelor în model pentru afișare.
  Răspuns: Returnează pagina patientPages/viewTreatments cu lista tratamentelor.
