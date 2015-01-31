package iancawood.marcopolo;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.telephony.SmsMessage;
import android.util.Log;

import java.sql.Time;
import java.util.Calendar;

public class SMSListener extends BroadcastReceiver
{
    private final String TAG = "LISTENER";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.v(TAG, "TEXT MESSAGE RECEIVED!!!!");

        Bundle extras = intent.getExtras();

        String strMessage = "";

        if ( extras != null )
        {
            Object[] smsextras = (Object[]) extras.get( "pdus" );

            for ( int i = 0; i < smsextras.length; i++ )
            {
                SmsMessage smsmsg = SmsMessage.createFromPdu((byte[])smsextras[i]);

                String strMsgBody = smsmsg.getMessageBody().toString();
                String strMsgSrc = smsmsg.getOriginatingAddress();

                strMessage += "SMS from " + strMsgSrc + " : " + strMsgBody;

                Log.i(TAG, strMessage);

                if (strMessage.toLowerCase().contains("marco")) {
                    Log.i(TAG, "hey listen!!!!!!!!!!!!!!!!!!!!!");

                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
                    r.play();

                    Calendar c = Calendar.getInstance();

                    // android.intent.action.SET_ALARM ???
                    Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, "Polo!");
                    alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, c.get(Calendar.HOUR_OF_DAY));
                    alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, c.get(Calendar.MINUTE) + 1);

                    Log.i(TAG, c.get(Calendar.HOUR_OF_DAY) + " + " + c.get(Calendar.MINUTE)); // return the proper date and time

                    // THIS FAILS HERE!!!! I DON'T KNOW WHAT CONTEXT IS!
                    context.startActivity(alarmIntent);

                }
            }

        }

    }

}