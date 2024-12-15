const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();

exports.cleanupOldNotes = functions.https.onRequest((req, res) => {
  setInterval(async () => {
      const now = Date.now();
      const twoWeeksAgo = now - 14 * 24 * 60 * 60 * 1000; // 14 dni w milisekundach

      const notesRef = admin.database().ref('notes');
      
      try {
          const snapshot = await notesRef.once('value');
          const updates = {};
      
          snapshot.forEach((child) => {
            const note = child.val();
            if (note.timestamp && note.timestamp < twoWeeksAgo) {
              updates[child.key] = null;
            }
          });
      
          if (Object.keys(updates).length > 0) {
              await notesRef.update(updates);
              console.log(`Usunięto ${Object.keys(updates).length} notatek starszych niż 2 tygodnie.`);
          } else {
              console.log('Brak notatek do usunięcia.');
          }
      } catch (error) {
          console.error('Błąd podczas czyszczenia notatek:', error);
      }

      res.send('Funkcja czyszcząca została uruchomiona.');
  }, 24 * 60 * 60 * 1000); // Uruchamia co 24 godziny

  return null;
});