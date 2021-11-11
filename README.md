# ViktorpizwAppodeal
Приложение находится в ветке master. Native реализовал через Recycler. 



Вопрос:
Hello, team!
We got an email from google play about violations of their rules. They told us that we place
interstitial ads so that they suddenly appear when a user is focused on a task at hand (e.g.
playing a game, filling out a form, reading content) may lead to accidental clicks and often
creates a frustrating user experience(see on the screenshot below).
Could you please describe how we can fix it?
Kind regards.




Ответ:

Hello!
To solve this problem, you must change the logic of the ad display.
Move the Appodeal.show (<youractivity>, Appodeal.INTERSTITIAL)
function to the part of the code that is responsible for navigating between Activities or Fragments.
If you have any questions, we will be happy to help you.
Best regards, Viktor!
