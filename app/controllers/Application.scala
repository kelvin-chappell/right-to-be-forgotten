package controllers

import model.NameExtractor
import play.api.mvc._
import play.api.libs.ws.WS
import scala.concurrent.ExecutionContext.Implicits.global

object Application extends Controller {

  def index = Action {
    NameExtractor(testBody) foreach println
    Ok(views.html.index("Your new application is ready."))
  }

  def checkUrl(url: String) = Action.async {
    WS.url(url).get() map { response =>
      val body = response.body
      val names = NameExtractor(body)
      Ok("Names to process: \n" + names.mkString(";"))
    }
  }

  val testBody = """
                   |
                   |    <div id="article-body-blocks">
                   |	    <p>Dougie McDonald, the referee who lied about his reasons for reversing a penalty for <a
                   |     href="http://www.theguardian.com/football/celtic" title="More from the Guardian on
                   |     Celtic">Celtic</a>, has announced his retirement.</p><p>McDonald had been under serious
                   |     pressure to step down after the admission that he lied about the penalty in a game against
                   |     Dundee United last month. Celtic's chairman John Reid had called directly for McDonald's his
                   |      removal after the club's recent annual general meeting; the referee's position was widely
                   |      regarded as untenable.</p><p>McDonald's exit comes after a weekend in which Scotland's <a
                   |      href="http://www.theguardian.com/football/referees" title="More from the Guardian on
                   |      Referees">referees</a> went on strike in protest at what they perceive as escalating abuse
                   |      towards them and the questioning of their honesty.</p><p>Hugh Dallas,
                   |      the Scottish Football Association's head of refereeing,
                   |      also left his post on Friday. Dallas, who is known to be close to McDonald,
                   |      had been under investigation for the alleged sending of an offensive email relating to the
                   |      pope's visit to Scotland.</p><p></p><p>McDonald said: "My category one colleagues decided
                   |      rightly to withdraw their services from matches this weekend in response to the outrageous
                   |      way they have been treated by sections within Scottish football and, in my opinion,
                   |      the lack of support they have received from the SFA general purposes committee in recent
                   |      years.</p><p>"However, their united stand, and the position of strength they have
                   |      established this weekend, has been clouded by one issue, namely the aftermath of the Dundee
                   |       United v Celtic match on 17 October. I apologised for my role in that and want my
                   |       previously unblemished 29-year career to move on."</p><p>"Now is the time for all of
                   |       Scottish football to move on.</p><p>"My decision will therefore remove that issue from the
                   |        debate and ensure that the next day of action — which, in my opinion,
                   |        will undoubtedly come if the football community does not have a massive change of heart —
                   |         will result in media coverage being concentrated on those who engage in referee bashing
                   |         and those who condone it."</p><p>Celtic refused comment on McDonald's decision
                   |         .</p><p></p><p>Neil Lennon, the Celtic manager, and the striker Gary Hooper will go
                   |         before the SFA tomorrow, in what will be the first test of the governing body's promise
                   |         to get tough on those who are critical of referees. Lennon has been referred after hefor
                   |          disciplinary action following comments made after a defeat to Hearts. Celtic's manager
                   |          claimed the officials would concoct a story as to why he had been sent to the stand
                   |          during  game.</p><p>Hooper said a disproportionate number of decisions were given
                   |          against Celtic because they "are one of the biggest teams in the world".As Scotland
                   |          recovers from its referees' strike, the SFA chief executive, Stewart Regan,
                   |          said those who question officials will be more firmly dealt with than before. That
                   |          stance has upset the country's Players Union. "We will be very tough on any player who
                   |          challenges or abuses referees in a way that is unacceptable,
                   |          " said Regan.</p><p>Referees return to action next weekend but the strike issue may
                   |          rumble on after claims the SFA misled foreign officials who had been brought in as
                   |          cover with four Premier League games going ahead on Saturday. Polish referees
                   |          backtracked on their offer to help after learning the reasons for the withdrawal of
                   |          labour and two of their Portuguese counterparts did likewise upon arriving in Glasgow
                   |          on Friday. The Israeli referee Eli Hacmon, who took charge of Kilmarnock's win over
                   |          Aberdeen on Saturday said: "We did not know the reason before we came on Friday. If we
                   |          knew it before, we would not have come here."</p>
                   |    </div>
                   |"""
}
