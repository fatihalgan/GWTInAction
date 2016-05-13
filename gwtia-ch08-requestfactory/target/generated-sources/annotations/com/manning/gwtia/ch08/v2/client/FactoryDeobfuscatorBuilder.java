// Automatically Generated -- DO NOT EDIT
// com.manning.gwtia.ch08.v2.client.Factory
package com.manning.gwtia.ch08.v2.client;
import java.util.Arrays;
import com.google.web.bindery.requestfactory.vm.impl.OperationData;
import com.google.web.bindery.requestfactory.vm.impl.OperationKey;
import com.google.gwt.core.shared.GwtIncompatible;
@GwtIncompatible("Server-side only but loaded through naming convention so must be in same package as shared Factory interface")
public final class FactoryDeobfuscatorBuilder extends com.google.web.bindery.requestfactory.vm.impl.Deobfuscator.Builder {
{
withOperation(new OperationKey("_M5qKMLlR0Yfc1RYPQ5YeED8aE8="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/lang/Integer;")
  .withMethodName("count")
  .withRequestContext("com.manning.gwtia.ch08.v2.client.Factory$ContactRequest")
  .build());
withOperation(new OperationKey("bU591OnRnN4gg8GRV$hW2BxPFts="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/manning/gwtia/ch08/v2/client/ContactProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/manning/gwtia/ch08/v2/server/Contact;)V")
  .withMethodName("remove")
  .withRequestContext("com.manning.gwtia.ch08.v2.client.Factory$ContactRequest")
  .build());
withOperation(new OperationKey("Oxt8H7HZFR2BGNjw3Rq35t_7caw="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/util/List;")
  .withMethodName("findAllContacts")
  .withRequestContext("com.manning.gwtia.ch08.v2.client.Factory$ContactRequest")
  .build());
withOperation(new OperationKey("XEmOJCGOdMG8KrGIrdnhLzuqBh8="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/Long;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/Long;)Lcom/manning/gwtia/ch08/v2/server/Contact;")
  .withMethodName("find")
  .withRequestContext("com.manning.gwtia.ch08.v2.client.Factory$ContactRequest")
  .build());
withOperation(new OperationKey("O2JIjOAn97ti14x9nPVyAcBdlP0="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/manning/gwtia/ch08/v2/client/ContactProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/manning/gwtia/ch08/v2/server/Contact;)V")
  .withMethodName("persist")
  .withRequestContext("com.manning.gwtia.ch08.v2.client.Factory$ContactRequest")
  .build());
withRawTypeToken("3zKRfUAq1Kb9j6a4p1okmyUxSow=", "com.manning.gwtia.ch08.v2.client.ContactProxy");
withRawTypeToken("w1Qg$YHpDaNcHrR5HZ$23y518nA=", "com.google.web.bindery.requestfactory.shared.EntityProxy");
withRawTypeToken("8KVVbwaaAtl6KgQNlOTsLCp9TIU=", "com.google.web.bindery.requestfactory.shared.ValueProxy");
withRawTypeToken("FXHD5YU0TiUl3uBaepdkYaowx9k=", "com.google.web.bindery.requestfactory.shared.BaseProxy");
withRawTypeToken("NpiBAALwiNhUgk1T73mJMUBH26I=", "com.manning.gwtia.ch08.v2.client.PhoneProxy");
withClientToDomainMappings("com.manning.gwtia.ch08.v2.server.Contact", Arrays.asList("com.manning.gwtia.ch08.v2.client.ContactProxy"));
withClientToDomainMappings("com.manning.gwtia.ch08.v2.server.Contact$Phone", Arrays.asList("com.manning.gwtia.ch08.v2.client.PhoneProxy"));
}}
