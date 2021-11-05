package pl.blaszak.remainder.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.Callable
import javax.swing.JOptionPane
import javax.swing.JOptionPane.INFORMATION_MESSAGE

class RemainderService(private var title: String, private var message: String, private var interval: Long) : Callable<Unit> {

    private var logger: Logger = LoggerFactory.getLogger(RemainderService::class.java)

    override fun call() {
        logger.info("Starting Service \'$title\': \'$message\'...")
        call(1)
    }

    private fun call(i: Int) {
        Thread.sleep(interval * 1000)
        logger.info("Shaving message \'$title\' dialog with \'$message\' $i time")
        JOptionPane.showMessageDialog(null, message, title, INFORMATION_MESSAGE)
        call(i + 1)
    }
}

