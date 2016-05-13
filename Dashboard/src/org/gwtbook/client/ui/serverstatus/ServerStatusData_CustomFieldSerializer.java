package org.gwtbook.client.ui.serverstatus;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

public class ServerStatusData_CustomFieldSerializer {

	public static ServerStatusData instantiate(SerializationStreamReader reader) throws SerializationException {
		return new ServerStatusData();
	}
	
	public static void serialize(SerializationStreamWriter writer, ServerStatusData instance) throws SerializationException {
		writer.writeString(instance.serverName);
        writer.writeLong(instance.totalMemory);
        writer.writeLong(instance.freeMemory);
        writer.writeLong(instance.maxMemory);
        writer.writeInt(instance.threadCount);
	}
	
	public static void deserialize(SerializationStreamReader reader, ServerStatusData instance) throws SerializationException {
		instance.serverName = reader.readString();
        instance.totalMemory = reader.readLong();
        instance.freeMemory = reader.readLong();
        instance.maxMemory = reader.readLong();
        instance.threadCount = reader.readInt();
	}
}
