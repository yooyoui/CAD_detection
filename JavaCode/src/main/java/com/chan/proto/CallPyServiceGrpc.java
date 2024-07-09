package com.chan.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: CallPy.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CallPyServiceGrpc {

  private CallPyServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "CallPyProto.CallPyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.chan.proto.CallPyProto.ScriptRequest,
      com.chan.proto.CallPyProto.ScriptResponse> getExecuteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Execute",
      requestType = com.chan.proto.CallPyProto.ScriptRequest.class,
      responseType = com.chan.proto.CallPyProto.ScriptResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.chan.proto.CallPyProto.ScriptRequest,
      com.chan.proto.CallPyProto.ScriptResponse> getExecuteMethod() {
    io.grpc.MethodDescriptor<com.chan.proto.CallPyProto.ScriptRequest, com.chan.proto.CallPyProto.ScriptResponse> getExecuteMethod;
    if ((getExecuteMethod = CallPyServiceGrpc.getExecuteMethod) == null) {
      synchronized (CallPyServiceGrpc.class) {
        if ((getExecuteMethod = CallPyServiceGrpc.getExecuteMethod) == null) {
          CallPyServiceGrpc.getExecuteMethod = getExecuteMethod =
              io.grpc.MethodDescriptor.<com.chan.proto.CallPyProto.ScriptRequest, com.chan.proto.CallPyProto.ScriptResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Execute"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chan.proto.CallPyProto.ScriptRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chan.proto.CallPyProto.ScriptResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CallPyServiceMethodDescriptorSupplier("Execute"))
              .build();
        }
      }
    }
    return getExecuteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CallPyServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CallPyServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CallPyServiceStub>() {
        @java.lang.Override
        public CallPyServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CallPyServiceStub(channel, callOptions);
        }
      };
    return CallPyServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CallPyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CallPyServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CallPyServiceBlockingStub>() {
        @java.lang.Override
        public CallPyServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CallPyServiceBlockingStub(channel, callOptions);
        }
      };
    return CallPyServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CallPyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CallPyServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CallPyServiceFutureStub>() {
        @java.lang.Override
        public CallPyServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CallPyServiceFutureStub(channel, callOptions);
        }
      };
    return CallPyServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void execute(com.chan.proto.CallPyProto.ScriptRequest request,
        io.grpc.stub.StreamObserver<com.chan.proto.CallPyProto.ScriptResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExecuteMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CallPyService.
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public static abstract class CallPyServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CallPyServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CallPyService.
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public static final class CallPyServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CallPyServiceStub> {
    private CallPyServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallPyServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CallPyServiceStub(channel, callOptions);
    }

    /**
     */
    public void execute(com.chan.proto.CallPyProto.ScriptRequest request,
        io.grpc.stub.StreamObserver<com.chan.proto.CallPyProto.ScriptResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExecuteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CallPyService.
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public static final class CallPyServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CallPyServiceBlockingStub> {
    private CallPyServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallPyServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CallPyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.chan.proto.CallPyProto.ScriptResponse execute(com.chan.proto.CallPyProto.ScriptRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExecuteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CallPyService.
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public static final class CallPyServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CallPyServiceFutureStub> {
    private CallPyServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallPyServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CallPyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.chan.proto.CallPyProto.ScriptResponse> execute(
        com.chan.proto.CallPyProto.ScriptRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExecuteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EXECUTE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EXECUTE:
          serviceImpl.execute((com.chan.proto.CallPyProto.ScriptRequest) request,
              (io.grpc.stub.StreamObserver<com.chan.proto.CallPyProto.ScriptResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getExecuteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.chan.proto.CallPyProto.ScriptRequest,
              com.chan.proto.CallPyProto.ScriptResponse>(
                service, METHODID_EXECUTE)))
        .build();
  }

  private static abstract class CallPyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CallPyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.chan.proto.CallPyProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CallPyService");
    }
  }

  private static final class CallPyServiceFileDescriptorSupplier
      extends CallPyServiceBaseDescriptorSupplier {
    CallPyServiceFileDescriptorSupplier() {}
  }

  private static final class CallPyServiceMethodDescriptorSupplier
      extends CallPyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CallPyServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CallPyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CallPyServiceFileDescriptorSupplier())
              .addMethod(getExecuteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
