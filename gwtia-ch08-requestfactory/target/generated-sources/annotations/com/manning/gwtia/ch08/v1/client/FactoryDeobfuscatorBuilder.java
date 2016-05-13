// Automatically Generated -- DO NOT EDIT
// com.manning.gwtia.ch08.v1.client.Factory
package com.manning.gwtia.ch08.v1.client;
import java.util.Arrays;
import com.google.web.bindery.requestfactory.vm.impl.OperationData;
import com.google.web.bindery.requestfactory.vm.impl.OperationKey;
import com.google.gwt.core.shared.GwtIncompatible;
@GwtIncompatible("Server-side only but loaded through naming convention so must be in same package as shared Factory interface")
public final class FactoryDeobfuscatorBuilder extends com.google.web.bindery.requestfactory.vm.impl.Deobfuscator.Builder {
{
withOperation(new OperationKey("roHwwcIpY6kfacPC5Xb4zVz7eao="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/lang/Integer;")
  .withMethodName("count")
  .withRequestContext("com.manning.gwtia.ch08.v1.client.Factory$ContactRequest")
  .build());
withOperation(new OperationKey("9WW1GmTkWNIqAb7v0CEDjZBJ49A="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/InstanceRequest;")
  .withDomainMethodDescriptor("()V")
  .withMethodName("remove")
  .withRequestContext("com.manning.gwtia.ch08.v1.client.Factory$ContactRequest")
  .build());
withOperation(new OperationKey("8mPltjoFnMquwbepvVtmmus7$S0="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/util/List;")
  .withMethodName("findAllContacts")
  .withRequestContext("com.manning.gwtia.ch08.v1.client.Factory$ContactRequest")
  .build());
withOperation(new OperationKey("K2qZwjAJTCAFLZ871FfJBCpc6i0="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/Long;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/Long;)Lcom/manning/gwtia/ch08/v1/server/Contact;")
  .withMethodName("find")
  .withRequestContext("com.manning.gwtia.ch08.v1.client.Factory$ContactRequest")
  .build());
withOperation(new OperationKey("8F_8sV56_rlHpVyR35C6Uk9FldQ="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/InstanceRequest;")
  .withDomainMethodDescriptor("()V")
  .withMethodName("persist")
  .withRequestContext("com.manning.gwtia.ch08.v1.client.Factory$ContactRequest")
  .build());
withRawTypeToken("HTbiDt$tdie9p6bBySp8nWQJSGw=", "com.manning.gwtia.ch08.v1.client.ContactProxy");
withRawTypeToken("w1Qg$YHpDaNcHrR5HZ$23y518nA=", "com.google.web.bindery.requestfactory.shared.EntityProxy");
withRawTypeToken("XOiGqyan104nV8FlriwhhZr7gwc=", "com.manning.gwtia.ch08.v1.client.ContactProxy$PhoneProxy");
withRawTypeToken("8KVVbwaaAtl6KgQNlOTsLCp9TIU=", "com.google.web.bindery.requestfactory.shared.ValueProxy");
withRawTypeToken("FXHD5YU0TiUl3uBaepdkYaowx9k=", "com.google.web.bindery.requestfactory.shared.BaseProxy");
withClientToDomainMappings("com.manning.gwtia.ch08.v1.server.Contact", Arrays.asList("com.manning.gwtia.ch08.v1.client.ContactProxy"));
withClientToDomainMappings("com.manning.gwtia.ch08.v1.server.Contact$Phone", Arrays.asList("com.manning.gwtia.ch08.v1.client.ContactProxy$PhoneProxy"));
}}
