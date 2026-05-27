# Java 21 + Spring Boot 3.3.12 — Student Results Portal
## Complete Project Plan & Feature Coverage Guide

---

## Project Overview

**Name:** Student Results Portal  
**Purpose:** Students log in and view their own results across 3 subjects. Admins manage students, enter marks, update and delete records.  
**Subjects:** Maths · Science · English  
**Roles:** `STUDENT` (read own results only) · `ADMIN` (full CRUD on all students and marks)

### Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 (Eclipse Temurin 21.0.10+9) |
| Framework | Spring Boot 3.3.12 |
| Database | PostgreSQL 16 |
| Migrations | Flyway |
| Cache | Redis 7 |
| Frontend | Next.js 14 · React 18 · Tailwind CSS |
| Build | Maven 3.9 |
| Containers | Docker + Docker Compose |
| Testing | JUnit 5 · Testcontainers · MockMvc |

### Domain at a Glance

```
Student      has many   Mark
Subject      has many   Mark
Mark         belongs to Student + Subject
User         linked to  Student (login credentials)
AuditLog     records    every create / update / delete
```

Entities: `Student`, `Subject`, `Mark`, `User`, `Role`, `AuditLog`  
Subjects seeded at startup: `MATHS`, `SCIENCE`, `ENGLISH`

---

## Part 1 — Java Evolution: Java 8 to Java 21

Every feature below appears naturally in this project. Nothing is a forced demo.

---

### Java 8

| Feature | Where Used in Project |
|---|---|
| Lambda expressions | Filter/sort student lists in service layer |
| Stream API | Calculate subject averages, total marks, pass/fail counts |
| Functional interfaces | Custom mark validators composed with `and()` |
| Default methods | `Auditable` interface with `isRecentlyModified()` default |
| Optional | Nullable result lookups — `findById`, `findByStudentCode` |
| Date & Time API | Exam dates, result timestamps, `Duration` for audit age |
| CompletableFuture | Async report generation, async email dispatch |
| Nashorn / Scripting | Not used — correctly deprecated path |

### Java 9

| Feature | Where Used in Project |
|---|---|
| Collection factory methods | `List.of("MATHS", "SCIENCE", "ENGLISH")` subject config |
| Stream improvements — `takeWhile`, `dropWhile` | Take top-N results until grade drops below threshold |
| Stream improvements — `ofNullable` | Bridge optional filter params into stream pipeline |
| `Optional.or()` | Fallback: find by student code or by email |
| `Optional.ifPresentOrElse()` | Log found vs not-found in lookup service |
| Private interface methods | Shared validation logic inside `ResultRepository` interface |
| Multi-Release JARs | Noted as concept; not directly implemented |

### Java 10

| Feature | Where Used in Project |
|---|---|
| `var` — local variable type inference | All service method bodies — `var students = repo.findAll()` |
| App Class-Data Sharing | Faster restarts in dev; configured in JVM flags |
| GC improvements | Noted in JVM configuration section |

### Java 11 (LTS)

| Feature | Where Used in Project |
|---|---|
| `java.net.http.HttpClient` | Call external grade import API (HTTP/2, timeout, async) |
| `String.isBlank()` | Input sanitisation on student name, roll number |
| `String.lines()` | Parse multi-line bulk import CSV content |
| `String.strip()` | Trim student input before persistence |
| `String.repeat()` | Generate test data patterns in dev seeder |
| `var` in lambda params | `(@NotNull var mark) -> validate(mark)` |
| Single-file source execution | Quick utility scripts for DB seed |

### Java 12 — 13

| Feature | Where Used in Project |
|---|---|
| Switch expressions (preview → standard in 14) | Grade letter mapping: marks range → `A / B / C / D / F` |
| Text blocks (preview → standard in 15) | SQL query constants, email templates |
| JVM Constants API | Noted as internal JVM feature |

### Java 14 — 15

| Feature | Where Used in Project |
|---|---|
| Records (preview → standard in 16) | `StudentDTO`, `MarkDTO`, `ResultSummary`, `CreateMarkRequest` |
| Pattern matching `instanceof` (preview → standard in 16) | Event type checking in notification handler |
| Helpful NullPointerExceptions | Easier debugging — enabled by default |
| Switch expressions standard | Grade mapping finalised without preview flag |
| Text blocks standard | SQL and email templates finalised |
| Sealed classes (preview → standard in 17) | `ServiceResult` hierarchy begins here |

### Java 16 — 17 (LTS)

| Feature | Where Used in Project |
|---|---|
| Records standard | All DTOs and request/response objects are records |
| Pattern matching `instanceof` standard | `if (event instanceof MarkUpdatedEvent e)` |
| Sealed classes standard | `ServiceResult` — `Success`, `NotFound`, `Forbidden`, `ValidationError` |
| Strong encapsulation of JDK internals | Cleaner reflection; no illegal access warnings |
| Foreign Function API (incubator) | Noted — not used in this project |

### Java 18 — 20

| Feature | Where Used in Project |
|---|---|
| UTF-8 by default | Student names with Unicode characters handled correctly |
| Virtual threads (preview) | Async email and report tasks |
| Structured concurrency (incubator) | Fetch marks for all 3 subjects in parallel |
| Record patterns (preview) | Destructure `ServiceResult.Success(var student)` in switch |
| Scoped values (incubator) | Pass logged-in user context without `ThreadLocal` |

### Java 21 (LTS) — Final Target

| Feature | Where Used in Project |
|---|---|
| Virtual threads **standard** | `spring.threads.virtual.enabled=true` — all I/O work |
| Record patterns **standard** | `case Success(StudentDTO(var id, var name, _, _)) ->` |
| Pattern matching for switch **standard** | Controller dispatches `ServiceResult` with full switch |
| Sequenced collections | `results.getFirst()` / `getLast()` on sorted mark lists |
| Scoped values (preview) | `ScopedValue<UserContext>` — request-scoped principal |
| String templates (preview) | Email body, log messages, SQL snippet construction |
| Unnamed patterns & variables | `case NotFound(_) ->` in switch arms |
| Generational ZGC | JVM flag `-XX:+UseZGC -XX:+ZGenerational` in prod config |

---

## Part 2 — Spring Boot Version Evolution: 2.5 to 3.3

---

### Spring Boot 2.5

| Feature | Where Used |
|---|---|
| Flyway enhancements | V1–V5 migrations — students, subjects, marks, users, audit_log tables |
| SQL script initialisation improvements | Dev seed data loaded cleanly on startup |
| Docker image buildpacks | `mvn spring-boot:build-image` produces OCI image |
| Graceful shutdown | In-flight requests complete before app stops |
| Config data improvements | `application.yml` with profile-specific overrides |

### Spring Boot 2.6

| Feature | Where Used |
|---|---|
| PathPatternParser (default) | `/students/{id}/results`, `/admin/students/{id}/marks` routing |
| Better Redis support | Redis auto-configuration for result caching |
| SameSite cookie support | Secure session cookies for student login |
| Circular dependency prohibition | Catches wiring errors early — forced good architecture |
| Health group improvements | Separate liveness / readiness probes |

### Spring Boot 2.7

| Feature | Where Used |
|---|---|
| Auto-configuration registration change | `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` |
| Improved observability groundwork | Prepares for Micrometer Observation in Boot 3 |
| Spring Security updates | OAuth2 / JWT groundwork carried into Boot 3 |
| Better native image preparation | AOT hints start here |

### Spring Boot 3.0

| Feature | Where Used |
|---|---|
| **Jakarta EE 9 migration** | All `javax.*` → `jakarta.*` — persistence, validation, servlet |
| **Java 17 baseline** | Project requires Java 17 minimum (we target 21) |
| **Spring Framework 6** | New web abstractions, HTTP Interface clients |
| Hibernate 6 | Improved JPQL, better type handling, `@JdbcType` |
| **Problem Details** (RFC 7807) | `ProblemDetail` returned from all exception handlers |
| Micrometer Observation API | `@Observed` on service methods |
| AOT processing | Compile-time bean processing for native builds |
| GraalVM native image | Optional — `./mvnw native:compile` |

### Spring Boot 3.1

| Feature | Where Used |
|---|---|
| **Docker Compose auto-start** | `compose.yml` with postgres + redis — auto-launched in dev |
| **Testcontainers integration** | `@ServiceConnection` — zero-config postgres in tests |
| SSL bundle configuration | HTTPS in dev with auto-reload |
| Service connections | `@ServiceConnection` on `@Container` — no manual URL config |
| Improved native image support | Testcontainers native compatibility |

### Spring Boot 3.2

| Feature | Where Used |
|---|---|
| **Virtual threads support** | `spring.threads.virtual.enabled=true` — Tomcat + @Async |
| **RestClient API** | Replaces `RestTemplate` for external grade import calls |
| Structured logging | JSON log format with `logging.structured.format.console=ecs` |
| CRaC support | Checkpoint/restore for fast startup — noted in config |
| Observability enhancements | Automatic spans on HTTP requests and DB queries |

### Spring Boot 3.3

| Feature | Where Used |
|---|---|
| Improved Docker Compose integration | Profiles, `depends_on`, named service connections |
| SSL Bundle enhancements | Runtime certificate reload without restart |
| CDS (Class Data Sharing) improvements | `spring.context.checkpoint=onRefresh` — faster cold start |
| Better startup performance | Fewer beans initialised at startup |
| Observability improvements | Spans on repository method calls |
| Dependency alignment | Latest Hibernate, Jackson, Flyway, Micrometer versions |

---

## Part 3 — Spring Modules Coverage

---

### Spring Core — IoC, DI, Lifecycle

- Constructor injection throughout (no field injection)
- `@Component`, `@Service`, `@Repository`, `@Controller` stereotypes
- `@Bean` factory methods in `@Configuration` classes
- `BeanPostProcessor` — custom post-processing for audit setup
- `ApplicationRunner` — dev data seeder on startup
- `@Profile` — dev seeder only runs in `dev` profile
- `@Conditional` — conditional bean registration
- `@Lazy` — defer heavy beans until first use

### Spring MVC — REST API

- `@RestController` + `@RequestMapping` on all controllers
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@PatchMapping`, `@DeleteMapping`
- `@PathVariable`, `@RequestParam`, `@RequestBody`, `@AuthenticationPrincipal`
- `@Valid` on request bodies — bean validation integration
- `ResponseEntity<?>` for fine-grained HTTP status control
- `@ResponseStatus` for simple cases
- `@RestControllerAdvice` — global exception handler
- `ProblemDetail` — RFC 7807 error responses
- `SseEmitter` — `/results/stream` for real-time mark-published notifications
- HTTP Interface client — declarative client for external grade API
- `PageRequest` + `Pageable` — paginated admin student list
- `@CrossOrigin` — CORS for Next.js frontend

### Spring Security 6

- `SecurityFilterChain` — replaces `WebSecurityConfigurerAdapter`
- Stateless session — `SessionCreationPolicy.STATELESS`
- `JwtAuthFilter extends OncePerRequestFilter` — validates Bearer token
- `BCryptPasswordEncoder` — password hashing strength 12
- `AuthenticationManager` — login endpoint wires through this
- `@PreAuthorize("hasRole('ADMIN')")` — admin-only endpoints
- `@PreAuthorize("#studentId == authentication.principal.id")` — students see own data only
- `@PostAuthorize` — filter response fields by role
- `@EnableMethodSecurity` — activates method-level annotations
- OAuth2 login — Google login as alternative to username/password
- `OAuth2AuthenticationSuccessHandler` — create/link student account on first OAuth2 login
- `HttpStatusEntryPoint(UNAUTHORIZED)` — clean 401 for API clients
- Custom `AccessDeniedHandler` — clean 403 response

### Spring Data JPA

- `@Entity`, `@Table`, `@Column`, `@Id`, `@GeneratedValue` — all entities
- `@ManyToOne`, `@OneToMany`, `@ManyToMany` — relationships
- `FetchType.LAZY` everywhere — no N+1 problems
- `@SequenceGenerator` — PostgreSQL sequences, allocationSize 50
- Derived query methods — `findByStudentIdAndSubjectId`, `findByStudentId`
- `@Query` JPQL — average mark per subject, total marks, rank
- `@Query` native — full-text search on student names
- Interface projections — `ResultSummary` (id, subject, mark, grade only)
- `Page<T>` + `Pageable` — paginated admin views
- `Stream<T>` — stream all marks for bulk report generation
- `JpaSpecificationExecutor` — dynamic filtering (subject, grade, date range)
- `@EntityListeners(AuditingEntityListener.class)` — auto timestamps
- `@CreatedDate`, `@LastModifiedDate`, `@CreatedBy`, `@LastModifiedBy`
- `@Version` — optimistic locking on `Mark` (prevent concurrent overwrite)
- `@SQLDelete` + `@Filter` — soft delete on `Student`
- `existsBy` queries — duplicate check before insert

### Spring AOP

- `@Aspect` + `@Component` — all aspects
- `@Pointcut` — reusable pointcut expressions
- `@Around` — audit aspect wraps all `@Auditable` methods; measures duration
- `@Before` — log method entry with arguments
- `@AfterReturning` — log successful mark creation
- `@AfterThrowing` — log and alert on service exceptions
- `@After` — always-runs cleanup logging
- Custom `@Auditable` annotation — marks methods to audit
- Performance aspect — `@Around` on all `com.studentresults.service.*` — warn if > 500ms
- Retry aspect — `@Around` on transient DB errors with backoff

### Spring Cache

- `@EnableCaching` in configuration
- Redis `CacheManager` with TTL — 5 minutes for result pages
- `@Cacheable(value = "results", key = "#studentId")` — student result fetch
- `@CachePut(value = "results", key = "#result.studentId()")` — update cache on mark edit
- `@CacheEvict(value = "results", key = "#studentId")` — clear on delete
- `@CacheEvict(allEntries = true)` + `@Scheduled` — nightly full cache flush
- `GenericJackson2JsonRedisSerializer` — JSON serialisation in Redis

### Spring Events

- `ApplicationEventPublisher` — publish `MarksUpdatedEvent`, `StudentCreatedEvent`
- `@EventListener` — synchronous audit log write
- `@TransactionalEventListener(phase = AFTER_COMMIT)` — email fires only after DB commit
- `@Async` on event listeners — non-blocking on virtual thread executor
- Custom events: `MarksUpdatedEvent`, `StudentCreatedEvent`, `StudentDeletedEvent`

### Spring Scheduling

- `@EnableScheduling`
- `@Scheduled(cron = "0 0 7 * * MON-FRI")` — daily result digest email to students
- `@Scheduled(fixedDelay = 60_000)` — cleanup expired JWT blacklist entries
- `@Scheduled(fixedRate = 300_000)` — refresh subject config cache

### Spring Validation

- `@NotBlank`, `@Size`, `@Min`, `@Max`, `@NotNull` — on all request records
- Custom `@MarkRange` annotation + `MarkRangeValidator` — marks must be 0–100
- Custom `@UniqueRollNumber` — cross-field DB uniqueness check
- `@Validated` on controllers — triggers method-level validation
- Validation groups — `OnCreate` vs `OnUpdate` different rules

### Spring Configuration Properties

- `@ConfigurationProperties(prefix = "app")` as a `record`
- `@Validated` on properties class
- Nested records for `Email`, `Jwt`, `Storage` config groups
- `application.yml` + `application-dev.yml` + `application-prod.yml`
- Secrets from environment variables — `${JWT_SECRET}`, `${DB_PASSWORD}`

### Spring Actuator + Micrometer

- Endpoints exposed: `health`, `info`, `metrics`, `prometheus`, `loggers`, `env`
- `AbstractHealthIndicator` — custom `ResultServiceHealthIndicator`
- `MeterRegistry` — custom `Counter` (marks created), custom `Timer` (result fetch latency)
- `@Timed` — automatic timer on controller methods
- `@Observed` — automatic tracing span on service methods
- Structured logging — JSON format with trace/span IDs
- Prometheus scrape endpoint for dashboarding

### Spring Profiles + DevTools

- `dev` profile — Docker Compose auto-start, verbose SQL logging, seed data
- `test` profile — Testcontainers, mock email service, no scheduled jobs
- `prod` profile — connection pool tuning, structured logs, no SQL echo
- Spring Boot DevTools — live reload in dev

### Spring Boot Testing

- `@SpringBootTest(webEnvironment = RANDOM_PORT)` — full stack integration tests
- `@WebMvcTest(StudentController.class)` — controller slice, no DB
- `@DataJpaTest` — repository slice with real SQL
- `@Testcontainers` + `@Container PostgreSQLContainer` — real DB in tests
- `@ServiceConnection` — zero-config container wiring (Boot 3.1)
- `@DynamicPropertySource` — override datasource URL from container
- `MockMvc` — HTTP-level controller assertions
- `@WithMockUser(roles = "STUDENT")` / `@WithMockUser(roles = "ADMIN")`
- `@MockBean` — mock services in controller tests
- `@ParameterizedTest` + `@CsvSource` — test grade boundaries (0, 34, 35, 49, 50, 74, 75, 100)

---

## Part 4 — Project Structure

```
student-results/
├── backend/
│   ├── src/main/java/com/studentresults/
│   │   ├── config/              SecurityConfig, CacheConfig, AsyncConfig, JpaConfig
│   │   ├── controller/          StudentController, ResultController, AdminController, AuthController
│   │   ├── service/             StudentService, ResultService, AuthService, NotificationService
│   │   ├── repository/          StudentRepository, MarkRepository, SubjectRepository
│   │   ├── domain/              Student, Mark, Subject, User, Role, AuditLog (entities)
│   │   ├── dto/                 StudentDTO, MarkDTO, ResultSummary, CreateMarkRequest (records)
│   │   ├── event/               MarksUpdatedEvent, StudentCreatedEvent, StudentDeletedEvent
│   │   ├── aspect/              AuditAspect, PerformanceAspect, RetryAspect
│   │   ├── scheduler/           DigestScheduler, CacheRefreshScheduler
│   │   ├── security/            JwtService, JwtAuthFilter, OAuth2SuccessHandler
│   │   ├── exception/           StudentNotFoundException, GlobalExceptionHandler
│   │   ├── properties/          AppProperties (record)
│   │   └── metrics/             ResultMetrics
│   ├── src/main/resources/
│   │   ├── db/migration/        V1–V6 Flyway scripts
│   │   ├── application.yml
│   │   ├── application-dev.yml
│   │   └── application-prod.yml
│   └── src/test/
│       ├── integration/         StudentResultsIntegrationTest
│       ├── controller/          StudentControllerTest, AdminControllerTest
│       └── repository/          MarkRepositoryTest
│
├── frontend/
│   ├── app/
│   │   ├── (auth)/login/        Student login page
│   │   ├── (student)/results/   Student view — own marks
│   │   └── (admin)/
│   │       ├── students/        Admin — list, add, edit, delete students
│   │       └── marks/           Admin — enter/update marks per subject
│   ├── components/
│   │   ├── ResultCard.tsx        Shows mark + grade + pass/fail badge
│   │   ├── SubjectTable.tsx      3-subject marks table
│   │   ├── StudentForm.tsx       Add/edit student form
│   │   └── LiveNotification.tsx  SSE — shows "marks published" toast
│   ├── lib/
│   │   ├── api.ts                Typed fetch wrapper
│   │   └── auth.ts               NextAuth config
│   └── hooks/
│       └── useResults.ts         SWR fetch hook
│
├── compose.yml                   postgres + redis (auto-started by Spring Boot 3.1+)
└── README.md
```

---

## Part 5 — Database Schema (Flyway Migrations)

| Migration | File | What it creates |
|---|---|---|
| V1 | `V1__create_users.sql` | `users`, `roles`, `user_roles` tables |
| V2 | `V2__create_students.sql` | `students` table with soft delete |
| V3 | `V3__create_subjects.sql` | `subjects` table + 3 seed rows |
| V4 | `V4__create_marks.sql` | `marks` table with FK to student + subject |
| V5 | `V5__create_audit_log.sql` | `audit_log` table |
| V6 | `V6__add_indexes.sql` | Indexes on marks(student_id), marks(subject_id) |

---

## Part 6 — API Endpoints

### Auth
| Method | Path | Role | Purpose |
|---|---|---|---|
| POST | `/api/auth/login` | Public | Username + password → JWT |
| POST | `/api/auth/register` | Public | Self-registration (student role) |
| POST | `/api/auth/refresh` | Any | Refresh JWT |

### Student — own data
| Method | Path | Role | Purpose |
|---|---|---|---|
| GET | `/api/results/me` | STUDENT | Own results — all 3 subjects |
| GET | `/api/results/me/summary` | STUDENT | Total marks, average, rank |

### Admin — student management
| Method | Path | Role | Purpose |
|---|---|---|---|
| GET | `/api/admin/students` | ADMIN | Paginated student list |
| POST | `/api/admin/students` | ADMIN | Add new student |
| GET | `/api/admin/students/{id}` | ADMIN | Student detail + marks |
| PUT | `/api/admin/students/{id}` | ADMIN | Update student info |
| DELETE | `/api/admin/students/{id}` | ADMIN | Soft delete student |

### Admin — marks management
| Method | Path | Role | Purpose |
|---|---|---|---|
| POST | `/api/admin/students/{id}/marks` | ADMIN | Enter marks (all 3 subjects) |
| PUT | `/api/admin/marks/{markId}` | ADMIN | Update a single mark |
| DELETE | `/api/admin/marks/{markId}` | ADMIN | Delete a mark |

### Notifications
| Method | Path | Role | Purpose |
|---|---|---|---|
| GET | `/api/results/stream` | STUDENT | SSE stream — mark-published events |

---

## Part 7 — Grading Rules

| Marks (out of 100) | Grade | Status |
|---|---|---|
| 75 – 100 | A | Pass |
| 50 – 74 | B | Pass |
| 35 – 49 | C | Pass |
| 0 – 34 | F | Fail |

Implemented as a `switch` expression on `int` marks — demonstrates Java 14+ switch expressions and Java 21 pattern matching.

---

## Part 8 — Build Phases

| Phase | What Gets Built | Java 21 Features | Spring Boot Features |
|---|---|---|---|
| 1 | Project scaffold, Docker Compose, Flyway | — | Boot 3.1 Compose auto-start, Flyway V1–V6 |
| 2 | JPA entities + repositories | Records as DTOs, `var` | Spring Data JPA — all patterns |
| 3 | Java language layer (8→16) | Lambdas, Streams, Optional, `var`, switch expressions, text blocks, pattern matching | Service layer uses all of these |
| 4 | REST API + validation + error handling | Records for request/response | MVC, @Valid, custom constraints, ProblemDetail |
| 5 | Spring Security — JWT + roles | — | SecurityFilterChain, JWT filter, method security |
| 6 | Sealed classes + Java 21 patterns | Sealed `ServiceResult`, record patterns, unnamed patterns | Controller switch dispatch |
| 7 | AOP aspects | — | @Aspect, @Around, @Before, @AfterThrowing |
| 8 | Cache + Events + Scheduling | `CompletableFuture`, string templates | Redis cache, ApplicationEvents, @Scheduled |
| 9 | Virtual threads + structured concurrency | Virtual threads, `StructuredTaskScope`, `ScopedValue` | `spring.threads.virtual.enabled=true` |
| 10 | Actuator + Micrometer | — | Health indicators, custom metrics, Prometheus |
| 11 | Testing — full coverage | — | SpringBootTest, WebMvcTest, DataJpaTest, Testcontainers |
| 12 | Next.js 14 frontend | — | CORS, SSE, JWT cookie auth |

---

## Part 9 — Java 21 Feature Quick Reference

| Feature | JEP | Status in 21 | Used For |
|---|---|---|---|
| Virtual threads | JEP 444 | Standard | All async I/O — email, reports |
| Record patterns | JEP 440 | Standard | Destructure ServiceResult in switch |
| Pattern matching for switch | JEP 441 | Standard | Controller result dispatch |
| Sequenced collections | JEP 431 | Standard | `getFirst()` / `getLast()` on mark lists |
| Scoped values | JEP 446 | Preview | Request-scoped user context |
| String templates | JEP 430 | Preview | Email body, log messages |
| Unnamed patterns & variables | JEP 443 | Preview | Wildcard switch arms |
| Generational ZGC | JEP 439 | Standard | Production JVM GC config |

---

## Part 10 — Spring Boot Module Quick Reference

| Module | Annotation / Class | Where |
|---|---|---|
| Security filter | `OncePerRequestFilter` | `JwtAuthFilter` |
| Method security | `@PreAuthorize`, `@PostAuthorize` | `ResultService`, `AdminService` |
| JPA auditing | `@CreatedDate`, `@LastModifiedBy` | All entities |
| Optimistic lock | `@Version` | `Mark` entity |
| Soft delete | `@SQLDelete`, `@Filter` | `Student` entity |
| AOP audit | `@Around`, custom `@Auditable` | `AuditAspect` |
| Cache | `@Cacheable`, `@CacheEvict` | `ResultService` |
| Events | `@TransactionalEventListener` | `NotificationService` |
| Scheduling | `@Scheduled(cron=...)` | `DigestScheduler` |
| Validation | custom `@MarkRange` | `CreateMarkRequest` |
| Error handling | `ProblemDetail` | `GlobalExceptionHandler` |
| Metrics | `Counter`, `Timer` | `ResultMetrics` |
| Testing | `@ServiceConnection` | `StudentResultsIntegrationTest` |
| HTTP client | `@HttpExchange` | `GradeImportClient` |
| Config | `@ConfigurationProperties` record | `AppProperties` |

---

*This plan covers: Java 8 → 21 full evolution · Spring Boot 2.5 → 3.3 version history · All major Spring modules · 12 build phases · Simple student domain that makes every feature necessary and natural.*
