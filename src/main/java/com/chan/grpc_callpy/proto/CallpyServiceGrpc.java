package com.chan.grpc_callpy.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: callpy.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CallpyServiceGrpc {

  private CallpyServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "callpy.CallpyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.chan.grpc_callpy.proto.CallpyProto.ScriptRequest,
      com.chan.grpc_callpy.proto.CallpyProto.ScriptResponse> getExecuteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Execute",
      requestType = com.chan.grpc_callpy.proto.CallpyProto.ScriptRequest.class,
      responseType = com.chan.grpc_callpy.proto.CallpyProto.ScriptResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.chan.grpc_callpy.proto.CallpyProto.ScriptRequest,
      com.chan.grpc_callpy.proto.CallpyProto.ScriptResponse> getExecuteMethod() {
    io.grpc.MethodDescriptor<com.chan.grpc_callpy.proto.CallpyProto.ScriptRequest, com.chan.grpc_callpy.proto.CallpyProto.ScriptResponse> getExecuteMethod;
    if ((getExecuteMethod = CallpyServiceGrpc.getExecuteMethod) == null) {
      synchronized (CallpyServiceGrpc.class) {
        if ((getExecuteMethod = CallpyServiceGrpc.getExecuteMethod) == null) {
          CallpyServiceGrpc.getExecuteMethod = getExecuteMethod =
              io.grpc.MethodDescriptor.<com.chan.grpc_callpy.proto.CallpyProto.ScriptRequest, com.chan.grpc_callpy.proto.CallpyProto.ScriptResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Execute"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chan.grpc_callpy.proto.CallpyProto.ScriptRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chan.grpc_callpy.proto.CallpyProto.ScriptResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CallpyServiceMethodDescriptorSupplier("Execute"))
              .build();
        }
      }
    }
    return getExecuteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CallpyServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CallpyServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CallpyServiceStub>() {
        @java.lang.Override
        public CallpyServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CallpyServiceStub(channel, callOptions);
        }
      };
    return CallpyServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CallpyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CallpyServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CallpyServiceBlockingStub>() {
        @java.lang.Override
        public CallpyServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CallpyServiceBlockingStub(channel, callOptions);
        }
      };
    return CallpyServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CallpyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CallpyServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CallpyServiceFutureStub>() {
        @java.lang.Override
        public CallpyServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CallpyServiceFutureStub(channel, callOptions);
        }
      };
    return CallpyServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     *定义执行方法，方法名称和参数和返回值都是大驼峰
     *Note: 这里是 returns,不是 return
     * </pre>
     */
    default void execute(com.chan.grpc_callpy.proto.CallpyProto.ScriptRequest request,
        io.grpc.stub.StreamObserver<com.chan.grpc_callpy.proto.CallpyProto.ScriptResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExecuteMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CallpyService.
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public static abstract class CallpyServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CallpyServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CallpyService.
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public static final class CallpyServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CallpyServiceStub> {
    private CallpyServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallpyServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CallpyServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *定义执行方法，方法名称和参数和返回值都是大驼峰
     *Note: 这里是 returns,不是 return
     * </pre>
     */
    public void execute(com.chan.grpc_callpy.proto.CallpyProto.ScriptRequest request,
        io.grpc.stub.StreamObserver<com.chan.grpc_callpy.proto.CallpyProto.ScriptResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExecuteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CallpyService.
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public static final class CallpyServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CallpyServiceBlockingStub> {
    private CallpyServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallpyServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CallpyServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *定义执行方法，方法名称和参数和返回值都是大驼峰
     *Note: 这里是 returns,不是 return
     * </pre>
     */
    public com.chan.grpc_callpy.proto.CallpyProto.ScriptResponse execute(com.chan.grpc_callpy.proto.CallpyProto.ScriptRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExecuteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CallpyService.
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public static final class CallpyServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CallpyServiceFutureStub> {
    private CallpyServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallpyServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CallpyServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *定义执行方法，方法名称和参数和返回值都是大驼峰
     *Note: 这里是 returns,不是 return
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.chan.grpc_callpy.proto.CallpyProto.ScriptResponse> execute(
        com.chan.grpc_callpy.proto.CallpyProto.ScriptRequest request) {
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
          serviceImpl.execute((com.chan.grpc_callpy.proto.CallpyProto.ScriptRequest) request,
              (io.grpc.stub.StreamObserver<com.chan.grpc_callpy.proto.CallpyProto.ScriptResponse>) responseObserver);
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
              com.chan.grpc_callpy.proto.CallpyProto.ScriptRequest,
              com.chan.grpc_callpy.proto.CallpyProto.ScriptResponse>(
                service, METHODID_EXECUTE)))
        .build();
  }

  private static abstract class CallpyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CallpyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.chan.grpc_callpy.proto.CallpyProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CallpyService");
    }
  }

  private static final class CallpyServiceFileDescriptorSupplier
      extends CallpyServiceBaseDescriptorSupplier {
    CallpyServiceFileDescriptorSupplier() {}
  }

  private static final class CallpyServiceMethodDescriptorSupplier
      extends CallpyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CallpyServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (CallpyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CallpyServiceFileDescriptorSupplier())
              .addMethod(getExecuteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
