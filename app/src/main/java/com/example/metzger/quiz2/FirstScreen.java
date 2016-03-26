package com.example.metzger.quiz2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.logging.Handler;

public class FirstScreen extends AppCompatActivity
{
    private BluetoothAdapter BA;
    UUID uid = UUID.fromString( "b6623047-f2f5-4500-b278-922a5c9b116c" );
    ArrayAdapter mArrayAdapter;
    Handler mHandler;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent discoverableIntent = new Intent( BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE );
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);

        BA = BluetoothAdapter.getDefaultAdapter();
        Intent intent = new Intent( BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE );
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(intent);

        final BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                String action = intent.getAction();

                if( BluetoothDevice.ACTION_FOUND.equals( action ) )
                {
                    BluetoothDevice device = intent.getParcelableExtra( BluetoothDevice.EXTRA_DEVICE );
                    mArrayAdapter.add( device.getName() + "\n" + device.getAddress() );
                }
            }
        };

        IntentFilter filter = new IntentFilter( BluetoothDevice.ACTION_FOUND );
        registerReceiver( mReceiver, filter );
    }

    public void nextPage( View view )
    {
        String buttonText;
        buttonText = ((Button) view).getText().toString();
        if( buttonText.equals( "New Game" ) )
        {
            Intent intent = new Intent( this, SetupScreen.class );
            startActivity( intent );
        }
        else
        {
            if( buttonText.equals( "Continue Game" ) )
            {
                Intent intent = new Intent( this, PlayerScreen.class );
                startActivity( intent );
            }
            else
            {
                if( buttonText.equals( "Shop" ) )
                {
                    Intent intent = new Intent( this, ShopScreen.class );
                    startActivity( intent );
                }
                else
                {
                    Intent intent = new Intent( this, CreateNewPlayer.class );
                    startActivity( intent );
                }
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class AcceptThread extends Thread
    {
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread()
        {
            BluetoothServerSocket temp = null;
            try
            {
                temp = BA.listenUsingRfcommWithServiceRecord( NAME, uid );
            }
            catch( IOException e ) {}
            mmServerSocket = temp;
        }

        public void run()
        {
            BluetoothSocket socket = null;

            while( true )
            {
                try
                {
                    socket = mmServerSocket.accept();
                }
                catch( IOException e )
                {
                    break;
                }

                if( socket != null )
                {
                    ConnectedThread first = new ConnectedThread( socket );
                    //mmServerSocket.close();
                    break;
                }
            }
        }

        public void cancel()
        {
            try
            {
                mmServerSocket.close();
            }
            catch( IOException e ) {}
        }
    }

    private class ConnectThread extends Thread
    {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread( BluetoothDevice device )
        {
            BluetoothSocket temp = null;
            mmDevice = device;

            try
            {
                temp = device.createRfcommSocketToServiceRecord( uid );
            }
            catch( IOException e ) {}
            mmSocket = temp;
        }

        public void run()
        {
            BA.cancelDiscovery();

            try
            {
                mmSocket.connect();
            }
            catch( IOException connectException )
            {
                try
                {
                    mmSocket.close();
                }
                catch( IOException closeException ) {}
                return;
            }

            ConnectedThread first = new ConnectedThread( mmSocket );
        }

        public void cancel()
        {
            try
            {
                mmSocket.close();
            }
            catch( IOException e ) {}
        }
    }

    private class ConnectedThread extends Thread
    {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread( BluetoothSocket socket )
        {
            mmSocket = socket;
            InputStream tempIn = null;
            OutputStream tempOut = null;

            try
            {
                tempIn = socket.getInputStream();
                tempOut = socket.getOutputStream();
            }
            catch( IOException e ) {}

            mmInStream = tempIn;
            mmOutStream = tempOut;
        }

        public void run()
        {
            byte[] buffer = new byte[ 1024 ];
            int bytes;

            while( true )
            {
                try
                {
                    bytes = mmInStream.read( buffer );
                    mHandler.obtainMessage( MESSAGE_READ, bytes, -1, buffer ).sendToTarget();
                }
                catch( IOException e )
                {
                    break;
                }
            }
        }

        public void write( byte[] bytes )
        {
            try
            {
                mmOutStream.write( bytes );
            }
            catch ( IOException e ) {}
        }

        public void cancel()
        {
            try
            {
                mmSocket.close();
            }
            catch( IOException e ) {}
        }
    }
}
